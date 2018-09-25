using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using NAudio.Wave;
using NAudio.Utils;
using System.Collections;
using System.Numerics;
namespace guitar_tuner_final_build
{
    /*
     * Robert Hill
     * Guitar Tuner
     * 3rd year Project @ University Center Blackburn College
     */


    public partial class Form1 : Form
    {

        public BufferedWaveProvider bwp;
        private WaveIn sourceStream = null;
        private DirectSoundOut waveOut = null;

        private const int sampleRate = 44100;
        private int BUFFERSIZE = (int)Math.Pow(2, 12);
        private double target;

        //Notes for standard tuning six string guitar, currently not an option
        private string[] openStringSix = { "E2", "A2", "D3", "G3", "B3", "E4" };
        private double[] standardTuningSix = { 82.41, 110.11, 146.8, 196.0, 246.9, 329.6 };

        //Notes for standard tuning eight string guitar, used in the demonstration
        //Frequencies are used in the Goertzel filter algorithm
        //also outputted to the user to show what to tune to
        private string[] openStringEight =     { "F#1", "B1", "E2",  "A2", "D3", "G3", "B3",  "E4"};
        private double[] standardTuningEight = { 46.25, 61.74, 82.41, 110.11, 146.8, 196.0, 246.9, 329.6};
        private string[] processingAlgorithm = { "Goertzel Filter", "Fast Fourier Transform"/*, "Descrete Fourier Transform", "FFT Harmonic Spectrum" */};

        private string algorithm;
        private double freq = 0;

        public Form1()
        {

            InitializeComponent();
            fillInput();

            foreach (var s in openStringEight)
            {
                cmbString.Items.Add(s);
            }

            foreach (var a in processingAlgorithm)
            {
                cmbAlgorithm.Items.Add(a);
            }
        }

        private void btnStart_Click(object sender, EventArgs e)
        {
            btnEnd.Visible = true;
            btnStart.Visible = false;

            if (cmbInput.Text == null) return;
            if (cmbAlgorithm.Text == null) return;

            algorithm = cmbAlgorithm.Text;

            int deviceNumber = cmbInput.SelectedIndex;
            int index = cmbString.SelectedIndex;
            target = standardTuningEight[index];
            txtTarget.Text = target.ToString();

            //begins the wave in stream
            sourceStream = new WaveIn();
            sourceStream.DeviceNumber = deviceNumber;
            sourceStream.BufferMilliseconds = (int)((double)BUFFERSIZE / (double)sampleRate * 1000.0);
            sourceStream.WaveFormat = new WaveFormat(sampleRate, 16, WaveIn.GetCapabilities(deviceNumber).Channels);

            //creates the buffer
            sourceStream.DataAvailable += new EventHandler<WaveInEventArgs>(sourceStream_DataAvailable);
            bwp = new BufferedWaveProvider(sourceStream.WaveFormat);
            bwp.BufferLength = BUFFERSIZE * 2;
            bwp.DiscardOnBufferOverflow = true;


            WaveInProvider waveIn = new WaveInProvider(sourceStream);

            waveOut = new DirectSoundOut();
            waveOut.Init(waveIn);

            sourceStream.StartRecording();
            waveOut.Play();

        }

        private void btnEnd_Click(object sender, EventArgs e)
        {
            if (waveOut != null)
            {
                waveOut.Stop();
                waveOut.Dispose();
                waveOut = null;
            }

            if (sourceStream != null)
            {
                sourceStream.StopRecording();
                sourceStream.Dispose();
                sourceStream = null;
            }

            btnEnd.Visible = false;
            btnStart.Visible = true;

        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            btnEnd_Click(sender, e);
            this.Close();
        }

        private void btnRefresh_Click(object sender, EventArgs e)
        {
            fillInput();
        }

        private void fillInput()
        {
            List<WaveInCapabilities> sources = new List<WaveInCapabilities>();

            for (int i = 0; i < WaveIn.DeviceCount; i++)
            {
                sources.Add(WaveIn.GetCapabilities(i));
            }

            cmbInput.Items.Clear();

            foreach (var source in sources)
            {
                

                if(source.ProductName == "Microphone (2- USB Audio Device")
                {
                    cmbInput.Items.Add("Ibanez RG 8");
                }
                else
                {
                    cmbInput.Items.Add(source.ProductName);
                }
                
            }
        }



        void sourceStream_DataAvailable(object sender, WaveInEventArgs e)
        {

            timer1.Tick += new EventHandler(freqTimer);
           timer1.Start();

            bwp.AddSamples(e.Buffer, 0, e.BytesRecorded);

            var buffer = new List<float>();

            for (int i = 0; i < e.BytesRecorded; i += 2)
            {
                //reverse bits
               short sample = (short)((e.Buffer[i + 1] << 8) | e.Buffer[i + 0]);

                //normalise sample values
                float sample32 = sample / 32768f;
                buffer.Add(sample32);
            }
            tuner(buffer.ToArray(), e.BytesRecorded);
        }


        private void tuner(float[] buffer, int end)
        {
            if(algorithm == "Goertzel Filter"){

                freq = ProcessingLogic.GoertzelFilter(buffer, target, buffer.Length);
            }
            else if( algorithm == "Fast Fourier Transform")
            {
                freq = ProcessingLogic.fundamentalFrequency(buffer);
            }
            else if(algorithm == "FFT Harmonic Spectrum")
            {
                Complex[] samples = new Complex[buffer.Length];

                for (int i = 0; i < buffer.Length; i++)
                {
                    samples[i] = buffer[i];
                }
                ProcessingLogic.FFT(samples);
                freq = ProcessingLogic.HPS(samples);
                //txtFreq.Text = freq.ToString();
            }
            else if( algorithm == "Descrete Fourier Transform")
            {
                Complex[] samples = new Complex[buffer.Length];

                for(int i = 0; i < buffer.Length; i++)
                {
                    samples[i] = buffer[i];
                }
                ProcessingLogic.DFT(samples);
                freq = ProcessingLogic.HPS(samples);
            }
        }


        private void freqTimer(object sender, EventArgs e)
        {
            outputFreq(freq);
            txtNotePlayed.Text = ProcessingLogic.notePlayed(freq);
        }

 
        private void outputFreq(double freq)
        {
         
            if (freq <= 10) return;

            if (freq > target + 200)
            {
                txtCenter.BackColor = Color.Red; txtLeft1.BackColor = Color.Red; txtLeft2.BackColor = Color.Red; txtLeft3.BackColor = Color.Red;
                txtRight1.BackColor = Color.Black; txtRight2.BackColor = Color.Black; txtRight3.BackColor = Color.Black;
            }
            else if (freq < target - 200)
            {
                txtCenter.BackColor = Color.Red; txtRight1.BackColor = Color.Red; txtRight2.BackColor = Color.Red; txtRight3.BackColor = Color.Red;
                txtLeft1.BackColor = Color.Black; txtLeft2.BackColor = Color.Black; txtLeft3.BackColor = Color.Black;
            }

            else if (freq >= target - 120 && freq <= target + 120)
            {
                double roundF = Math.Round((double)freq, 2);
                txtFreq.Text = roundF.ToString();

                if (freq >= target - 30 && freq <= target - 59.99)
                {
                    txtCenter.BackColor = Color.Red; txtRight1.BackColor = Color.Red; txtRight2.BackColor = Color.Red;
                    txtRight3.BackColor = Color.Black;
                    txtLeft1.BackColor = Color.Black; txtLeft2.BackColor = Color.Black; txtLeft3.BackColor = Color.Black;
                }
                else if (freq >= target - 5 && freq <= target - 29.99)
                {
                    txtCenter.BackColor = Color.Red; txtRight1.BackColor = Color.Red;
                    txtRight3.BackColor = Color.Black; txtRight2.BackColor = Color.Black;
                    txtLeft1.BackColor = Color.Black; txtLeft2.BackColor = Color.Black; txtLeft3.BackColor = Color.Black;
                }
                else if (freq >= target - 4.99 && freq <= target + 4.99)
                {
                    txtCenter.BackColor = Color.Green;
                    txtRight3.BackColor = Color.Black; txtRight2.BackColor = Color.Black; txtRight1.BackColor = Color.Black;
                    txtLeft1.BackColor = Color.Black; txtLeft2.BackColor = Color.Black; txtLeft3.BackColor = Color.Black;
                }
                else if (freq >= target + 5 && freq <= target + 29.99)
                {
                    txtCenter.BackColor = Color.Red; txtLeft1.BackColor = Color.Red;
                    txtLeft3.BackColor = Color.Black; txtLeft2.BackColor = Color.Black;
                    txtRight1.BackColor = Color.Black; txtRight2.BackColor = Color.Black; txtRight3.BackColor = Color.Black;
                }
                else if (freq >= target + 30 && freq <= target + 59.99)
                {
                    txtCenter.BackColor = Color.Red; txtLeft1.BackColor = Color.Red; txtLeft2.BackColor = Color.Red;
                    txtLeft3.BackColor = Color.Black;
                    txtLeft1.BackColor = Color.Black; txtLeft2.BackColor = Color.Black; txtLeft3.BackColor = Color.Black;
                }
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            

        }

        private void lblFreq_Click(object sender, EventArgs e)
        {

        }

        private void cmbString_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (cmbString.Text.Length > 0)
            {
                btnStart.Visible = true;
            }
        }

        private void cmbInput_SelectedIndexChanged(object sender, EventArgs e)
        {
            cmbString.Visible = true;
        }

        private void btnHelp_Click(object sender, EventArgs e)
        {
            frmHelp help = new frmHelp();
            help.Show();
        }
    }
}
