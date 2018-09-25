using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Numerics;


/*
 * copyright for code:
 *  FindFundamentalFrequency
 *  ScanSignalIntervals
 *  FindPeaks
 *  Calculate
 *  Log2
 *  
 *  @ notmymasteryet on codeproject usage under the MIT open source License
 *  https://www.codeproject.com/Articles/32172/FFT-Guitar-Tuner
 *  https://opensource.org/licenses/mit-license.php
 */

namespace guitar_tuner_final_build
{
    

    class ProcessingLogic
    {
        const int sampleRate = 44100;


        internal static Complex[] FFT(Complex[] samples)
        {

            int N = samples.Length;
            int k = 0;
            Complex[] X = new Complex[N];
            Complex[] e, E, d, D;

            //If num of samples equals 1, only that sample is returned
            if (N == 1)
            {
                X[0] = X[0];
                return X;
            }

            //enumerator and demonitor
            e = new Complex[N / 2];
            d = new Complex[N / 2];


            for (k = 0; k < N / 2; k++)
            {
                e[k] = X[k * 2];
                d[k] = X[k * 2 + 1];
            }

            E = FFT(e);
            D = FFT(d);

            // multiplys each element of the odd fourier transform array with its corresponding
            //complex number
            for (k = 0; k < N / 2; k++)
            {
                Complex temp = Complex.FromPolarCoordinates(1, -2 * Math.PI * k / N);
                D[k] *= temp;
            }

            //carries out addition and subtraction for each value
            //values between 0 & N/2 -1, each even value is added to each odd value
            //values between N/2 & N-1, each odd value is subtracted from each even value
            for (k = 0; k < N / 2; k++)
            {
                X[k] = E[k] + D[k];
                X[k + N / 2] = E[k] - D[k];
            }

            //returns the final array containing the fourier transform
            return X;
        }


        //Discrete Foutier Transform
        //Takes an arguement of complex time-domain samples
        internal static Complex[] DFT(Complex[] samples)
        {
            //number of samples in the time domain
            int N = samples.Length;
            Complex[] X = new Complex[N];

            //k represents the frequency for which the fourier transform is being calculated
            for (int k = 0; k < N; k++)
            {
                X[k] = new Complex(0, 0);

                for (int n = 0; n < N; n++)
                {
                    Complex temp = Complex.FromPolarCoordinates(1, -2 * Math.PI * n * k / N);
                    temp *= samples[n];
                    X[k] += temp;
                }
            }
            //Returns an array of spectram data
            return X;
        }

        //down sampling or decimation
        //keeps only N (factor) num of samples
        internal static Complex[] decimation(Complex[] samples, int factor)
        {
            Complex[] temp = new Complex[Convert.ToInt32(Math.Ceiling(samples.Length * 1.0 / factor))];

            for(int i = 0; i < temp.Length; i++)
            {
                temp[i] = samples[i * factor];
            }
            return temp;
        }


        //Harmonic Product Spectrum
        //Used for fundamentalfrequency detection
        //keeps only positive frequencys  from the fourier transform
        internal static double HPS(Complex[] samples)
        {
            //down sampled 5 times
            Complex[] hps2 = decimation(samples, 2);
            Complex[] hps3 = decimation(samples, 3);
            Complex[] hps4 = decimation(samples, 4);
            Complex[] hps5 = decimation(samples, 5);

            double[] temp = new double[hps5.Length];

            for(int i = 0; i < hps5.Length; i++)
            {
                checked
                {
                    temp[i] = Math.Sqrt(samples[i].Magnitude * hps2[i].Magnitude * 
                                        hps3[i].Magnitude *  hps4[i].Magnitude * hps5[i].Magnitude); 
                }
            }


            double max = 0;
            double index = -1;

            for(int i = 0; i < temp.Length / 2; i++)
            {
                //checks for max bin
                if (temp[i] > max) { max = temp[i]; index = i; }
            }

            double freq = (max / samples.Length) * sampleRate;
            return freq;
        }





        //algorithm for detecting a single note
        internal static double GoertzelFilter(float[] samples, double targetFrequency, int end)
        {
            double sPrev = 0.0;
            double sPrev2 = 0.0;

            double normalizedFreq = targetFrequency / sampleRate;
            double coeff = 2 * Math.Cos(2 * Math.PI * normalizedFreq);

            for (int i = 0; i < end; i++)
            {
                double s = samples[i] + coeff * sPrev - sPrev2;
                sPrev2 = sPrev;
                sPrev = s;
            }

            double power = sPrev2 * sPrev2 + sPrev * sPrev - coeff * sPrev * sPrev2;
            return power;
        }


    
        //Calculate note and octave from frequency
        internal static string notePlayed(double freq)
        {
            int octave = 0;

            //incriments i through the power of 2
            //8 octaves
            for (int i = 1; i <= 256; i = i * 2)
            {
                if (freq / 16.35 == i + 0.5) return ("C" + octave);
                else if (freq / 17.32 <= i + 0.1 && freq / 17.32 >= i - 0.1) return ("C#" + octave);
                else if (freq / 18.35 <= i + 0.1 && freq / 18.35 >= i - 0.1) return ("D#" + octave);
                else if (freq / 19.45 <= i + 0.1 && freq / 19.45 >= i - 0.1) return ("D" + octave);
                else if (freq / 20.60 <= i + 0.1 && freq / 20.60 >= i - 0.1) return ("E" + octave);
                else if (freq / 21.83 <= i + 0.1 && freq / 21.83 >= i - 0.1) return ("F" + octave);
                else if (freq / 23.12 <= i + 0.1 && freq / 23.12 >= i + 0.1) return ("F#" + octave);
                else if (freq / 24.50 <= i + 0.1 && freq / 24.50 >= i + 0.1) return ("G" + octave);
                else if (freq / 25.96 <= i + 0.1 && freq / 25.96 >= i + 0.1) return ("G#" + octave);
                else if (freq / 27.5 <= i + 0.1 && freq / 27.5 >= i + 0.1) return ("A" + octave);
                else if (freq / 29.14 <= i + 0.1 && freq / 29.14 >= i + 0.1) return ("A#" + octave);
                else if (freq / 30.87 <= i + 0.1 && freq / 30.87 >= i + 0.1) return ("B" + octave);
                octave++;
            }
            return null;  
        }

        //return mean value from sample set
        private float calculateMean(float[] samples)
        {
            float sum = 0;
            for (int i = 0; i < samples.Length; i++) sum += samples[i];

            return sum / samples.Length;
        }






        internal static double fundamentalFrequency(float[] samples)
        {
            
            const float minFreq = 20;
            const float maxFreq = 1400;
            float[] spectrogram = Calculate(samples);

            int usefullMinSpectr = Math.Max(0, (int)(minFreq * spectrogram.Length / sampleRate));
            int usefullMaxSpectr = Math.Min(spectrogram.Length, (int)(maxFreq * spectrogram.Length / sampleRate) + 1);

            // find peaks in the FFT frequency bins 
            const int PeaksCount = 5;
            int[] peakIndices;

            peakIndices = FindPeaks(spectrogram, usefullMinSpectr, usefullMaxSpectr - usefullMinSpectr, PeaksCount);

            if (Array.IndexOf(peakIndices, usefullMinSpectr) >= 0)
            {
                // lowest usefull frequency bin shows active
                // looks like is no detectable sound, return 0
                return 0;
            }

            // select fragment to check peak values: data offset
            const int verifyFragmentOffset = 0;
            // ... and half length of data
            int verifyFragmentLength = (int)(sampleRate / minFreq);

            // trying all peaks to find one with smaller difference value
            double minPeakValue = Double.PositiveInfinity;
            int minPeakIndex = 0;
            int minOptimalInterval = 0;

            for (int i = 0; i < peakIndices.Length; i++)
            {
                int index = peakIndices[i];
                int binIntervalStart = spectrogram.Length / (index + 1), binIntervalEnd = spectrogram.Length / index;
                int interval;
                double peakValue;

                // scan bins frequencies/intervals
                ScanSignalIntervals(samples, verifyFragmentOffset, verifyFragmentLength,
                    binIntervalStart, binIntervalEnd, out interval, out peakValue);

                if (peakValue < minPeakValue)
                {
                    minPeakValue = peakValue;
                    minPeakIndex = index;
                    minOptimalInterval = interval;
                }
            }
            return (double)sampleRate / minOptimalInterval * 2;
        }

        /// Calculates FFT using Cooley-Tukey FFT algorithm.
        /// If amount of data items not equal a power of 2, then algorithm
        /// automatically pad with 0s to the lowest amount of power of 2.
        public static float[] Calculate(float[] x)
        {
            int length;
            int bitsInLength;

            if (IsPowerOfTwo(x.Length))
            {
                length = x.Length;
                bitsInLength = Log2(length) - 1;
            }
            else
            {
                bitsInLength = Log2(x.Length);
                length = 1 << bitsInLength;
                // the items will be pad with zeros
            }

            // bit reversal
            Complex[] data = new Complex[length];
            for (int i = 0; i < x.Length; i++)
            {
                int j = ReverseBits(i, bitsInLength);
                data[j] = new Complex(x[i], 0);
            }

            //Cooley-Tukey 
            for (int i = 0; i < bitsInLength; i++)
            {
                int m = 1 << i;
                int n = m * 2;
                double alpha = -(2 * Math.PI / n);

                for (int k = 0; k < m; k++)
                {
                    //e^(-2*pi/N*k)
                    Complex oddPartMultiplier = new Complex(0, alpha * k);

                    double e = Math.Exp(oddPartMultiplier.Real);
                    oddPartMultiplier = new Complex(e * Math.Cos(oddPartMultiplier.Imaginary), e * Math.Sin(oddPartMultiplier.Imaginary));

                    for (int j = k; j < length; j += n)
                    {
                        Complex evenPart = data[j];
                        Complex oddPart = oddPartMultiplier * data[j + m];
                        data[j] = evenPart + oddPart;
                        data[j + m] = evenPart - oddPart;
                    }
                }
            }

            // calculate spectrogram
            float[] spectrogram = new float[length];
            for (int i = 0; i < spectrogram.Length; i++)
            {
                spectrogram[i] = (float)data[i].Real * (float)data[i].Real + (float)data[i].Imaginary * (float)data[i].Imaginary;
            }
            return spectrogram;
        }

        private static void ScanSignalIntervals(float[] samples, int index, int length,
            int intervalMin, int intervalMax, out int optimalInterval, out double optimalValue)
        {
            optimalValue = Double.PositiveInfinity;
            optimalInterval = 0;

            // distance between min and max range value can be big
            // limiting it to the fixed value
            const int MaxAmountOfSteps = 30;
            int steps = intervalMax - intervalMin;

            if (steps > MaxAmountOfSteps) steps = MaxAmountOfSteps;
            else if (steps <= 0) steps = 1;

            // trying all intervals in the range to find one with
            // smaller difference in signal waves
            for (int i = 0; i < steps; i++)
            {
                int interval = intervalMin + (intervalMax - intervalMin) * i / steps;

                double sum = 0;
                for (int j = 0; j < length; j++)
                {
                    double diff = samples[index + j] - samples[index + j + interval];
                    sum += diff * diff;
                }
                if (optimalValue > sum)
                {
                    optimalValue = sum;
                    optimalInterval = interval;
                }
            }
        }

        private static int[] FindPeaks(float[] values, int index, int length, int peaksCount)
        {
            float[] peakValues = new float[peaksCount];
            int[] peakIndices = new int[peaksCount];

            for (int i = 0; i < peaksCount; i++)
            {
                peakValues[i] = values[peakIndices[i] = i + index];
            }

            // find min peaked value
            float minStoredPeak = peakValues[0];
            int minIndex = 0;
            for (int i = 1; i < peaksCount; i++)
            {
                if (minStoredPeak > peakValues[i]) minStoredPeak = peakValues[minIndex = i];
            }

            for (int i = peaksCount; i < length; i++)
            {
                if (minStoredPeak < values[i + index])
                {
                    //replace the min peaked value with bigger one
                    peakValues[minIndex] = values[peakIndices[minIndex] = i + index];

                    //and find min peaked value again
                    minStoredPeak = peakValues[minIndex = 0];
                    for (int j = 1; j < peaksCount; j++)
                    {
                        if (minStoredPeak > peakValues[j]) minStoredPeak = peakValues[minIndex = j];
                    }
                }
            }
            return peakIndices;
        }
     
        //Gets number of significat bytes.
        private static int Log2(int n)
        {
            int i = 0;
            while (n > 0)
            {
                ++i; n >>= 1;
            }
            return i;
        }

        //Reverses bits in the number.
        private static int ReverseBits(int n, int bitsCount)
        {
            int reversed = 0;
            for (int i = 0; i < bitsCount; i++)
            {
                int nextBit = n & 1;
                n >>= 1;

                reversed <<= 1;
                reversed |= nextBit;
            }
            return reversed;
        }

        // Checks if number is power of 2.
        private static bool IsPowerOfTwo(int n)
        {
            return n > 1 && (n & (n - 1)) == 0;
        }
    }
}