// encoding task 4.cpp : Defines the entry point for the console application.
//
#include "iostream"
#include "iomanip"
#include "stdlib.h"
#include "stdio.h"
#include "string"
#include "time.h"
#include "fstream"
#include "vector"

using namespace std;

void mutalInformation(float *X, float *Y);
void messageEntropy(vector <string> buffer, char symbol, float *info);
char charValue(string chunck);
string decoder(string buffer);
string removeParity(vector <string> &buffer);
int oneCount(string block); 
int twoCount(string block);
int fourCount(string block);
int eightCount(string block);
bool binaryCheck(char p);
void errorChecking(vector <string> &buffer);
void noiseyChannel(vector <string> &errorEncoded);
void errorEncoded(vector <string> &binaryBuffer, vector <string> &encodedBuffer);
string errorbits(string block);
void binaryValue(char *buffer, int length, vector<string> &buffer2);

int main()
{
	const char *fileA = "../Encoding Task 4/files/FileA.txt";
	const char *outputFileErrors = "../Encoding Task 4/files/outputFileA.txt";
	FILE *pfile;
	long lSize;
	size_t result;
	char *buffer;

	vector<string> vBinaryBuffer;
	vBinaryBuffer = vector<string>();

	vector<string> vEncodedBuffer;
	vEncodedBuffer = vector<string>();


	pfile = fopen(fileA, "rb");
	if (pfile == NULL) { cout << "Error opening file\n"; return 1; }

	//obtain file size
	fseek(pfile, 0, SEEK_END);
	lSize = ftell(pfile);
	rewind(pfile);


	//allocate memory to store the whole file contents in buffer
	buffer = (char*)malloc(sizeof(char) *lSize);
	if (buffer == NULL) { cout << "Memory error\n"; return 0; }


	//copy the file into the buffer
	result = fread(buffer, 1, lSize, pfile);
	if (result != lSize) { cout << "Reading error\n"; return 0; }

	fclose(pfile);

	vBinaryBuffer.resize(lSize);

	binaryValue(buffer, lSize, vBinaryBuffer);

	errorEncoded(vBinaryBuffer, vEncodedBuffer);

	//Stores at position [0] entropy [1] one count probability, [2] zero Count probibility for input message, 
	float *inputX;	
	inputX = new float[3];
	
	messageEntropy(vEncodedBuffer, 'X', inputX);

	noiseyChannel(vEncodedBuffer); 


	//Stores at position [0] entropy [1] one count probability, [2] zero Count probibility for output message, 
	float *outputY;
	outputY = new float[3];

	messageEntropy(vEncodedBuffer, 'Y', outputY);

	mutalInformation(inputX, outputY);

	errorChecking(vEncodedBuffer);

	string out;
	out = removeParity(vEncodedBuffer);

	string decoderS;
	decoderS = decoder(out);


	cout << endl << " Running loop comparing message before and after noisey channel: " << endl;
	
	
	for (int i = 0; i < decoderS.length(); i++) {
		if (decoderS[i] != buffer[i]) 
			cout << "Char Num: " << i << " New messages contains: " << decoderS[i] << " : Expected " << buffer[i] << endl;
		if (i == 20)break;
	}



	ofstream write;
	write.open(outputFileErrors, ios::binary);
	write << decoderS;
	write.close();

	delete[] buffer;
	buffer = nullptr;
	delete[] inputX;
	inputX = nullptr;
	delete[] outputY;
	outputY = nullptr;
	return 0;
}


//calculates joint entropty
void mutalInformation(float *X, float *Y) {

	float HXY = 0.00; //joint entropy
	float IXY = 0.00; //mutual infomation

	float hxy = 0.00; //conditional entropy X
	float hyx = 0.00; //conditional entropy y
	
	HXY = X[1] * log2f(1.00 / X[1]) + X[2] * log2f(1.00 / X[2]) + Y[1] * log2f(1.00 / Y[1]) + Y[2] * log2f(Y[2]);
	
	cout << "\n\tJoint entropy for H(X,Y) = " << HXY << " bits" << endl;

	IXY = X[0] + Y[0] - HXY;

	cout << "\tMutual Information I(X,Y) = " << IXY << " bits" << endl;

	hxy = X[0] - IXY;

	cout << "\tConditional entropy H(X|Y) = " << hxy << " bits" << endl;

	hyx = Y[0] - IXY;

	cout << "\tConditional Entropy H(Y|X) = " << hyx <<  " bits"  << endl;


	//calculating channel noise
	float Hn = 0.00;
	float nProb = 1.00 / 15.00; // channel noise probability, one bit every 15;

	Hn = nProb * log2f(1.00 / nProb) + (1.00 - nProb) * log2f(1.00 / (1.00 - nProb));

	cout << "\tEntropy of Channel Noise H(n) = " << Hn << " bits" << endl;

	//calculating transmission efficiency
	float transEff = 0.00;

	transEff = IXY / Y[0];

	cout << "\tTransmission Efficiency = " << transEff << endl;

	//calculation Channel Capacity
	float C = 0.00;
	
	C = X[1] * X[0] - hxy;

	cout << "\tChannel Capacity C = " << C << " bits" << endl;

	//calculate channel rate
	float R = 0.00;
	float huffH = 4.27468;

	R = C / huffH;

	cout << "\t Maximum Channel Rate R  = " << R << endl;

}

//calculates and stores the entropy for the message
void messageEntropy(vector <string> buffer, char symbol, float *info) {

	float total = 0;
	float oneCount = 0;
	float zeroCount = 0;
	float oneProb = 0.00;
	float zeroProb= 0.00;

	for (int i = 0; i < buffer.size(); i++) {

		for (int j = 0; j < buffer[i].length(); j++) {

			if (buffer[i][j] == '1') {

				oneCount+=1;
				total+=1;
			}
			else if (buffer[i][j] == '0') {

				zeroCount += 1;
				total+=1;
			}

		}
	}

	cout << "\n\t" << symbol << " one count " << oneCount << endl;
	cout << "\t" << symbol << " zero count " << zeroCount << endl;
	cout << "\t" << symbol << " total count " << total << endl;


	oneProb = oneCount  / total;
	zeroProb = zeroCount / total;

	cout << "\t" << symbol << " one proportion = " << oneProb << endl;
	cout << "\t" << symbol << " zero proportion = " << zeroProb << endl;

	float H = 0.00;
	

	H = oneProb * log2f(1.00 / oneProb) + zeroProb * log2f(1.00 / zeroProb);

	cout << "\t H(" << symbol << ") = " << H << endl << endl;

	info[0] = H;
	info[1] = oneProb;
	info[2] = zeroProb;
}



string decoder(string buffer) {

	string tempString;
	string outBuffer;
	char tempChar;

	for (int i = 0; i < buffer.size(); i++) {

		for (int j = 3; j < 14; j++) {

			tempString = buffer.substr(i, j);

			if (tempString == "") break;

			//returns the char value for the data chunk
			tempChar = charValue(tempString);


			//if no char is return, skips the starting digit and moves on to the next
			//assumes there could be an error uncaught
			if (tempChar == '|' && j == 13) { i += 1; break; }

			//no char returned, increases size of chunk for checking
			else if (tempChar == '|') continue;

			//if char is returned adds it to the out buffer
			else { outBuffer += tempChar; i += j - 1; break; }

		}
	}
	return outBuffer;
}


//returns a string pointer with no parity bits
string removeParity(vector <string> &buffer) {

	string newBuffer;

	for (int i = 0; i < buffer.size(); i++) {

		//breaks if no more data
		if (buffer[i] == "")break;

		//removes paraity bits at position 0 - 1 - 3 - 7;
		buffer[i].erase(7, 1);
		buffer[i].erase(3, 1);
		buffer[i].erase(1, 1);
		buffer[i].erase(0, 1);

		newBuffer += buffer[i];

	}
	return newBuffer;
}


//checks for errors using huffman coding parity bits
void errorChecking(vector<string> &buffer) {

	int count = 0;
	int errorCount = 0;

	cout << endl << " Error Checking:  " << endl;

	for (int i = 0; i < buffer.size(); i++) {

		//breaks if no data is left in the buffer
		if (buffer[i] == "") break;
		if (buffer[i].length() < 15) break;


		//error checking for first parity bit, position 1
		count = oneCount(buffer[i]);
		if (count % 2 != 0) errorCount += 1;

		//error checking for second parity bit, position 2
		count = twoCount(buffer[i]);
		if (count % 2 != 0) errorCount += 2;

		//error checking for third parity bit, position 4
		count = fourCount(buffer[i]);
		if (count % 2 != 0) errorCount += 4;

		//error checking for fourth parity bit, position 8
		count = eightCount(buffer[i]);
		if (count % 2 != 0) errorCount += 8;

		
		//error correction
		if (errorCount > 0 && buffer[i][errorCount - 1] == '1') {
			
			//cout << "Packet Number " << i << " packet before: " << buffer[i] << " error at position: " << errorCount-1;
			buffer[i][errorCount - 1] = '0';
			//cout << " Packet After: " << buffer[i] << endl;
		}
		else if (errorCount > 0 && buffer[i][errorCount - 1] == '0') {

			//cout << "Packet Number " << i << " packet before: " << buffer[i] << " error at position: " << errorCount-1;
			buffer[i][errorCount - 1] = '1';
			//cout << " packet After: " << buffer[i] << endl;
		}
		errorCount = 0;
	}
}


void noiseyChannel(vector <string> &binaryBuffer) {

	//initaialise random seed
	srand(time(NULL));

	//generate random number
	int randNum = rand() % 100 + 1;

	cout << " Noisey Channel Adding Errors: \n";

	//loop through buffer
	for (int i = 0; i < binaryBuffer.size(); i++) {

		
		//if (i == randNum) {

			
			//flip random random bit
			randNum = rand() % 13 + 0;

			if (randNum >= binaryBuffer[i].size()) break;
			//if  (i == 10) break;

			//cout << " Packet Number " << i << "  : Packet Before: " << binaryBuffer[i] << " error at position: " << randNum;

			//check to see whether 1 or 0
			if (binaryBuffer[i][randNum] == '1') binaryBuffer[i][randNum] = '0';
			else binaryBuffer[i][randNum] = '1';

			//cout << "  Packet After: " << binaryBuffer[i] << endl;

			
			/*
			//flip random random bit
			randNum = rand() % 13 + 0;

			if (randNum >= binaryBuffer[i].size()) break;
			if  (i == 20) break;

			//cout << " Packet Number " << i << "  : Packet Before: " << binaryBuffer[i] << " error at position: " << randNum;

			//check to see whether 1 or 0
			if (binaryBuffer[i][randNum] == '1') binaryBuffer[i][randNum] = '0';
			else binaryBuffer[i][randNum] = '1';

			//cout << "  Packet After: " << binaryBuffer[i] << endl;
			
			/*
			//flip random random bit
			randNum = rand() % 13 + 0;

			if (randNum >= binaryBuffer[i].size()) break;
			if (i == 20) break;

			cout << " Packet Number " << i << "  : Packet Before: " << binaryBuffer[i] << " error at position: " << randNum;

			//check to see whether 1 or 0
			if (binaryBuffer[i][randNum] == '1') binaryBuffer[i][randNum] = '0';
			else binaryBuffer[i][randNum] = '1';

			cout << "  Packet After: " << binaryBuffer[i] << endl;
			

			//randNum = rand() % i + 100 + i;
			*/
		//}
	}
}




//8 bit error encoder
void errorEncoded(vector <string> &binaryBuffer, vector <string> &encodedBuffer) {

	int tempCount = 0;
	int encodedCount = 0;
	string tempblock;


	for (int i = 0; i < binaryBuffer.size(); i++) {

		for (int j = 0; j < binaryBuffer[i].length(); j++) {

			tempblock += binaryBuffer[i][j];
			tempCount++;

			//encodes a block of 11 digits
			if (tempCount > 1 && tempCount % 11 == 0) {
				
				encodedBuffer.push_back(errorbits(tempblock));
				encodedCount++;
				tempblock = "";
			}
		}
	}
}



string errorbits(string block) {

	//insert error bits & set them too zero
	block.insert(0, "0");
	block.insert(1, "0");
	block.insert(3, "0");
	block.insert(7, "0");

	//pairty bit one, position 1, count of 1s in block
	int count = oneCount(block);
	if (count % 2 != 0) block[0] = '1';

	//parity bit two, position 2, count of 1s in block
	count = twoCount(block);
	if (count % 2 != 0) block[1] = '1';

	//parity bit three, position 4, count of 1s in block
	count = fourCount(block);
	if (count % 2 != 0) block[3] = '1';

	//parity bit four, position 8, count of 1s in block
	count = eightCount(block);
	if (count % 2 != 0)block[7] = '1';

	//cout << block << endl;
	return block;
}




int oneCount(string block) {

	int count = 0;

	//check every other bit, positions 1-3-5
	for (int i = 0; i < block.length(); i += 2)
		if (block[i] == '1') count++;

	return count;
}


int twoCount(string block) {

	int count = 0;

	//check every other 2 bits,
	for (int j = 1; j < block.length(); j += 4) {
		if (block[j] == '1') count++;
		if (block[j + 1] == '1') count++;
	}
	return count;
}


int fourCount(string block) {

	int count = 0;

	//check every other ever other 4 bits,
	for (int k = 3; k < block.length(); k += 8)
		for (int p = k; p < k + 4; p++)
			if (block[p] == '1') count++;

	return count;
}


int eightCount(string block) {

	int count = 0;

	//error bit 4,  which checks ever other 8 bits
	for (int l = 7; l < block.length(); l++)
		if (block[l] == '1') count++;

	return count;
}


bool binaryCheck(char p) {

	if (p == '0') return false;
	else return true;
}




void binaryValue(char *buffer, int length, vector <string> &buffer2) {

	string binary;

	for (int i = 0; i < length; i++) {

		if (buffer[i] == 'e') binary = "000";
		else if (buffer[i] == ' ') binary = "110";
		else if (buffer[i] == 'r') binary = "0010";
		else if (buffer[i] == 'n') binary = "0100";
		else if (buffer[i] == 'l') binary = "0101";
		else if (buffer[i] == 'a') binary = "0111";
		else if (buffer[i] == 's') binary = "1000";
		else if (buffer[i] == 't') binary = "1001";
		else if (buffer[i] == 'u') binary = "1011";
		else if (buffer[i] == 'i') binary = "1111";
		else if (buffer[i] == 'm') binary = "11100";
		else if (buffer[i] == 'c') binary = "10100";
		else if (buffer[i] == 'o') binary = "01101";
		else if (buffer[i] == 'd') binary = "00110";
		else if (buffer[i] == 'v') binary = "001111";
		else if (buffer[i] == ',') binary = "011000";
		else if (buffer[i] == 'p') binary = "101010";
		else if (buffer[i] == '.') binary = "101011";
		else if (buffer[i] == 'q') binary = "1110111";
		else if (buffer[i] == 'b') binary = "1110110";
		else if (buffer[i] == 'g') binary = "1110101";
		else if (buffer[i] == 'f') binary = "0110011";
		else if (buffer[i] == 'N') binary = "00111000";
		else if (buffer[i] == '\n') binary = "00111011";
		else if (buffer[i] == 'h') binary = "11101000";
		else if (buffer[i] == 'P') binary = "111010010";
		else if (buffer[i] == 'I') binary = "011001010";
		else if (buffer[i] == 'S') binary = "011001001";
		else if (buffer[i] == 'M') binary = "011001000";
		else if (buffer[i] == 'D') binary = "001110101";
		else if (buffer[i] == 'C') binary = "001110011";
		else if (buffer[i] == 'V') binary = "001110010";
		else if (buffer[i] == 'Q') binary = "0011101000";
		else if (buffer[i] == 'j') binary = "0110010110";
		else if (buffer[i] == 'A') binary = "0110010111";
		else if (buffer[i] == 'x') binary = "1110100111";
		else if (buffer[i] == 'F') binary = "11101001100";
		else if (buffer[i] == 'U') binary = "11101001101";
		else if (buffer[i] == 'E') binary = "00111010011";
		else if (buffer[i] == ';') binary = "001110100101";
		else if (buffer[i] == 'L') binary = "0011101001000";
		else if (buffer[i] == 'O') binary = "0011101001001";

		buffer2[i] = binary;
	}
}


char charValue(string chunck) {

	if (chunck == "000") return 'e';
	else if (chunck == "110") return ' ';
	else if (chunck == "0010") return 'r';
	else if (chunck == "0100") return 'n';
	else if (chunck == "0101") return 'l';
	else if (chunck == "0111") return 'a';
	else if (chunck == "1000") return 's';
	else if (chunck == "1001") return 't';
	else if (chunck == "1011") return 'u';
	else if (chunck == "1111") return 'i';
	else if (chunck == "11100") return 'm';
	else if (chunck == "10100") return 'c';
	else if (chunck == "01101") return 'o';
	else if (chunck == "00110") return 'd';
	else if (chunck == "001111") return 'v';
	else if (chunck == "011000") return ',';
	else if (chunck == "101010") return 'p';
	else if (chunck == "101011") return '.';
	else if (chunck == "1110111") return 'q';
	else if (chunck == "1110110") return 'b';
	else if (chunck == "1110101") return 'g';
	else if (chunck == "0110011") return 'f';
	else if (chunck == "00111000") return 'N';
	else if (chunck == "00111011") return '\n';
	else if (chunck == "11101000") return 'h';
	else if (chunck == "111010010") return 'P';
	else if (chunck == "011001010") return 'I';
	else if (chunck == "011001001") return 'S';
	else if (chunck == "011001000") return 'M';
	else if (chunck == "001110101") return 'D';
	else if (chunck == "001110011") return 'C';
	else if (chunck == "001110010") return 'V';
	else if (chunck == "0011101000") return 'Q';
	else if (chunck == "0110010110") return 'j';
	else if (chunck == "0110010111") return 'A';
	else if (chunck == "1110100111") return 'x';
	else if (chunck == "11101001100") return 'F';
	else if (chunck == "11101001101") return 'U';
	else if (chunck == "00111010011") return 'E';
	else if (chunck == "001110100101") return ';';
	else if (chunck == "0011101001000") return 'L';
	else if (chunck == "0011101001001") return 'O';
	else return '|';
}
