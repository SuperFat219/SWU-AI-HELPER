/*-------------------------------------------------------------------
 * File name:    mytestdes.cpp
 *
 * Author:       Xiaomin Bao
 * Last updated: Feb. 22, 2001
 *-------------------------------------------------------------------*/
/*
  This code implements the encryption of DES, it reads in a key and a 
  plaintext (both in hexdecimal form) from files, then prints out the 
  ciphertext (binary form).
*/

#include <iostream>
#include <fstream>
#include <vector>
#include <iomanip>

using namespace std;

typedef vector<int> binVector;

//-------------------------------------------------------------------

binVector KeyIP(binVector);
binVector C_D_Finder(binVector, int);
void Print_List(binVector);
binVector K_Finder(binVector,int);
binVector IP(binVector);
binVector List_Extender(binVector);
binVector Lists_Plus(binVector, binVector);
binVector Get_Half_L(binVector, int);
binVector Func_f(binVector);
binVector R_Finder(binVector, binVector, binVector, int);
binVector Lists_Merger(binVector, binVector);
binVector InvIP(binVector);

// external function, the names of the file which contains it is
// hexToBinary.cpp

binVector hexToBinary(char *);

// external function, name of the file: binaryToHex.cpp

vector <char> binaryToHex(binVector);
//-------------------------------------------------------------------

int main(){

   char *keyInput  = new char[16];
   char *textInput = new char[16];

   binVector Key, plaintext;
   vector <char> ciphertext; // ciphertext in hexdecimal form

   binVector  c[17], d[17], k[16], L[17], R[17];

   ifstream keyIn, PlainTextIn;
   
   keyIn.open("keyHexForm.txt");
   if(!keyIn){
      cout << "Can not open the file." << endl;
      return 1;
   }   

   PlainTextIn.open("plaintextHexForm.txt");
   if(!PlainTextIn){
      cout << "Can not open the plaintext file." << endl;
      return 1;
   }   
   
   keyIn.read(keyInput, 16); // read in key

   Key = hexToBinary(keyInput);
   cout << "\n   The key (binary form) is:\n";
   Print_List(Key);
   
   PlainTextIn.read(textInput,16); // read in plaintext

   plaintext = hexToBinary(textInput);
    
   cout << "   The plaintext (binary form) is:\n";
   Print_List(plaintext);
   
   c[0]=Get_Half_L(KeyIP(Key),1);
   d[0]=Get_Half_L(KeyIP(Key),29);

   c[1]=C_D_Finder(c[0],1);
   d[1]=C_D_Finder(d[0],1);

   c[2]=C_D_Finder(c[1],1);
   d[2]=C_D_Finder(d[1],1);

   for(int i = 3; i <= 8; i++){
      c[i] = C_D_Finder(c[i-1],2);
      d[i] = C_D_Finder(d[i-1],2);
   }

   c[9]=C_D_Finder(c[8],1);
   d[9]=C_D_Finder(d[8],1);

   for(int i =10; i <= 15; i++){
      c[i] = C_D_Finder(c[i-1],2);
      d[i] = C_D_Finder(d[i-1],2);
   }

   c[16]=C_D_Finder(c[15],1);
   d[16]=C_D_Finder(d[15],1);

   for(int i = 0; i < 16; i++)
      k[i] = K_Finder(Lists_Merger(c[i+1],d[i+1]),i+1);
      
   L[0]=Get_Half_L(IP(plaintext),1);
   R[0]=Get_Half_L(IP(plaintext),33);

   for(int i = 1; i <= 16; i++){
     L[i] = R[i-1];
     R[i] = R_Finder(L[i-1],R[i-1],k[i-1],i);
   }

   cout << "   The cryptotext is:\n";
   Print_List(InvIP(Lists_Merger(R[16],L[16])));
   ciphertext = binaryToHex(InvIP(Lists_Merger(R[16],L[16])));

   for(int i=0; i < ciphertext.size(); i++)
      ciphertext[i];
   cout << endl;
   keyIn.close();
   PlainTextIn.close();

   return 0;
}//main

//--------------KeyIP------------------------------------------------
binVector KeyIP(binVector I){ // permute key
   binVector tmp;
   
   for(int i=1; i<=3; i++){
      for(int j=0; j<8; j++)
        tmp.push_back(I[55+i-8*j]);
   }
   for(int i=1;i<=4;i++)
      tmp.push_back(I[59-8*(i-1)]);
      
     
   for(int i=1; i<=3; i++){
      for(int j=0; j<8; j++)
        tmp.push_back(I[63-i-8*j]);
   }
   for(int i=1;i<=4;i++)
       tmp.push_back(I[27-8*(i-1)]);
       
   return tmp;
}//KeyIP

//--------------------C_D_Finder-------------------------------------
binVector C_D_Finder(binVector L, int shift){// find ci and di
   binVector tmp;
   
   for(int i=shift; i<L.size(); i++)     
      tmp.push_back(L[i]);      
   for(int j=0; j<shift; j++)
      tmp.push_back(L[j]);
   return tmp;
}//C_D_Finder

//-----------------------Print_List----------------------------------
void Print_List(binVector L){//print binary string, 8 bits per row
   cout <<"   -----------------------------------------------\n\n";
   for(int i=1; i <= L.size(); i++){
      cout << setw(6) << L[i-1];
      if(i%8==0)
         cout << endl;
   }
   cout << endl;
}//Print_List

//----------------K_Finder-------------------------------------------
binVector K_Finder(binVector C, int n){// find kn
   binVector tmp;
   
   tmp.push_back(C[13]);
   tmp.push_back(C[16]);
   tmp.push_back(C[10]);
   tmp.push_back(C[23]);
   tmp.push_back(C[0]);
   tmp.push_back(C[4]);
   
   tmp.push_back(C[2]);
   tmp.push_back(C[27]);
   tmp.push_back(C[14]);
   tmp.push_back(C[5]);
   tmp.push_back(C[20]);
   tmp.push_back(C[9]);
   
   tmp.push_back(C[22]);
   tmp.push_back(C[18]);
   tmp.push_back(C[11]);
   tmp.push_back(C[3]);
   tmp.push_back(C[25]);
   tmp.push_back(C[7]);
   
   tmp.push_back(C[15]);
   tmp.push_back(C[6]);
   tmp.push_back(C[26]);
   tmp.push_back(C[19]);
   tmp.push_back(C[12]);
   tmp.push_back(C[1]);
   
   tmp.push_back(C[40]);
   tmp.push_back(C[51]);
   tmp.push_back(C[30]);
   tmp.push_back(C[36]);
   tmp.push_back(C[46]);
   tmp.push_back(C[54]);
   
   tmp.push_back(C[29]);
   tmp.push_back(C[39]);
   tmp.push_back(C[50]);
   tmp.push_back(C[44]);
   tmp.push_back(C[32]);
   tmp.push_back(C[47]);
   
   tmp.push_back(C[43]);
   tmp.push_back(C[48]);
   tmp.push_back(C[38]);
   tmp.push_back(C[55]);
   tmp.push_back(C[33]);
   tmp.push_back(C[52]);
   
   tmp.push_back(C[45]);
   tmp.push_back(C[41]);
   tmp.push_back(C[49]);
   tmp.push_back(C[35]);
   tmp.push_back(C[28]);
   tmp.push_back(C[31]);
   
   //cout << "\nk" << n << " is:\n";
   //Print_List(tmp);
   
   return tmp;
}//K_Finder

//---------------IP--------------------------------------------------

binVector IP(binVector P){//find L0 and R0
   binVector tmp;
   
   for(int i=1;i<=4;i++){
      for(int j=1;j<=8;j++)
         tmp.push_back(P[57+2*(i-1)-8*(j-1)]);
   }
   for(int i=1;i<=4;i++){
      for(int j=1;j<=8;j++)
         tmp.push_back(P[56+2*(i-1)-8*(j-1)]);
   }
   return tmp;
}//IP

//-------------------------List_Extender-----------------------------
binVector List_Extender(binVector R){// Extend R from 32 bits to 48 bits
   binVector tmp;
   tmp.push_back(R[31]);

   for(int i=1;i<=5;i++)
      tmp.push_back(R[i-1]);

   for(int j=1;j<=7;j++){
      for(int l=0; l<6;l++)
         tmp.push_back(R[(3+4*(j-1)+l)%32]);
   }
   return tmp;
}//List_Extender

//----------------------Lists_Plus-----------------------------------

binVector Lists_Plus(binVector L, binVector R){//mod 2 sum of L and R
   binVector tmp;
   if(L.size()!=R.size())
      cout << " The list sizes are not equal.\n";
   for(int i=1; i<=R.size(); i++)
      tmp.push_back((L[i-1]+R[i-1])%2);
   return tmp;
}//Lists_Plus
     
//---------------------Get_Half_L------------------------------------
binVector Get_Half_L(binVector  long_list, int n){// get half part of a
   binVector tmp;                                 // list. If n=1, then
   for(int i=n; i<n+long_list.size()/2; i++)      // first part;if
      tmp.push_back(long_list[i-1]);              // n=(size()/2)+1,
   return tmp;                                    // then last part.
}//Get_Half_L

//-------------------Func_f--------------------------------------
binVector Func_f(binVector input){// perform the last permutation of f
   int data[8][4][16] = {
14,  4, 13,  1,  2, 15, 11,  8,  3, 10,  6, 12,  5,  9,  0,  7,
 0, 15,  7,  4, 14,  2, 13,  1, 10,  6, 12, 11,  9,  5,  3,  8,
 4,  1, 14,  8, 13,  6,  2, 11, 15, 12,  9,  7,  3, 10,  5,  0,
15, 12,  8,  2,  4,  9,  1,  7,  5, 11,  3, 14, 10,  0,  6, 13,

15,  1,  8, 14,  6, 11,  3,  4,  9,  7,  2, 13, 12,  0,  5, 10,
 3, 13,  4,  7, 15,  2,  8, 14, 12,  0,  1, 10,  6,  9, 11,  5,
 0, 14,  7, 11, 10,  4, 13,  1,  5,  8, 12,  6,  9,  3,  2, 15,
13,  8, 10,  1,  3, 15,  4,  2, 11,  6,  7, 12,  0,  5, 14,  9,

10,  0,  9, 14,  6,  3, 15,  5,  1, 13, 12,  7, 11,  4,  2,  8,
13,  7,  0,  9,  3,  4,  6, 10,  2,  8,  5, 14, 12, 11, 15,  1,
13,  6,  4,  9,  8, 15,  3,  0, 11,  1,  2, 12,  5, 10, 14,  7,
 1, 10, 13,  0,  6,  9,  8,  7,  4, 15, 14,  3, 11,  5,  2, 12,

 7, 13, 14,  3,  0,  6,  9, 10,  1,  2,  8,  5, 11, 12,  4, 15,
13,  8, 11,  5,  6, 15,  0,  3,  4,  7,  2, 12,  1, 10, 14,  9,
10,  6,  9,  0, 12, 11,  7, 13, 15,  1,  3, 14,  5,  2,  8,  4,
 3, 15,  0,  6, 10,  1, 13,  8,  9,  4,  5, 11, 12,  7,  2, 14,

 2, 12,  4,  1,  7, 10, 11,  6,  8,  5,  3, 15, 13,  0, 14,  9,
14, 11,  2, 12,  4,  7, 13,  1,  5,  0, 15, 10,  3,  9,  8,  6,
 4,  2,  1, 11, 10, 13,  7,  8, 15,  9, 12,  5,  6,  3,  0, 14,
11,  8, 12,  7,  1, 14,  2, 13,  6, 15,  0,  9, 10,  4,  5,  3,

12,  1, 10, 15,  9,  2,  6,  8,  0, 13,  3,  4, 14,  7,  5, 11,
10, 15,  4,  2,  7, 12,  9,  5,  6,  1, 13, 14,  0, 11,  3,  8,
 9, 14, 15,  5,  2,  8, 12,  3,  7,  0,  4, 10,  1, 13, 11,  6,
 4,  3,  2, 12,  9,  5, 15, 10, 11, 14,  1,  7,  6,  0,  8, 13,

 4, 11,  2, 14, 15,  0,  8, 13,  3, 12,  9,  7,  5, 10,  6,  1,
13,  0, 11,  7,  4,  9,  1, 10, 14,  3,  5, 12,  2, 15,  8,  6,
 1,  4, 11, 13, 12,  3,  7, 14, 10, 15,  6,  8,  0,  5,  9,  2,
 6, 11, 13,  8,  1,  4, 10,  7,  9,  5,  0, 15, 14,  2,  3, 12,

13,  2,  8,  4,  6, 15, 11,  1, 10,  9,  3, 14,  5,  0, 12,  7,
 1, 15, 13,  8, 10,  3,  7,  4, 12,  5,  6, 11,  0, 14,  9,  2,
 7, 11,  4,  1,  9, 12, 14,  2,  0,  6, 10, 13, 15,  3,  5,  8,
 2,  1, 14,  7,  4, 10,  8, 13, 15, 12,  9,  0,  3,  5,  6, 11 };

	int  p[8][6];
	int  bin[48],num[8];
	binVector output;
	int n, m;

	for( n = 0; n< 8; n++){
		for( m = 0; m<6; m++){
			p[n][m] = input[n*6+m];
		}
	}
	
	for( n = 0; n < 8; n++){
		int row, column;
		row = p[n][0]*2 + p[n][5];
		column= p[n][1]*8 + p[n][2]*4 + p[n][3]*2 +p[n][4];
		num[n] = data[n][row][column];
	}
	
	for( n = 0; n < 8; n++){
		switch (num[n]){
		case 0 :
				bin[n*4]   = 0;
				bin[n*4+1] = 0;
				bin[n*4+2] = 0;
				bin[n*4+3] = 0;
				break;

		case 1 : 	
				bin[n*4]   = 0;
			   	bin[n*4+1] = 0;
				bin[n*4+2] = 0;	
				bin[n*4+3] = 1;
				break;

		case 2 : 	
				bin[n*4]   = 0;
			   	bin[n*4+1] = 0;
				bin[n*4+2] = 1;	
				bin[n*4+3] = 0;
				break;

		case 3 : 	
				bin[n*4]   = 0;
			   	bin[n*4+1] = 0;
				bin[n*4+2] = 1;	
				bin[n*4+3] = 1;
				break;

		case 4 : 	
				bin[n*4]   = 0;
			   	bin[n*4+1] = 1;
				bin[n*4+2] = 0;	
				bin[n*4+3] = 0;
				break;

		case 5 : 	
				bin[n*4]   = 0;
			   	bin[n*4+1] = 1;
				bin[n*4+2] = 0;	
				bin[n*4+3] = 1;
				break;

		case 6 : 	
				bin[n*4]   = 0;
			   	bin[n*4+1] = 1;
				bin[n*4+2] = 1;	
				bin[n*4+3] = 0;
				break;

		case 7 : 	
				bin[n*4]   = 0;
			   	bin[n*4+1] = 1;
				bin[n*4+2] = 1;	
				bin[n*4+3] = 1;
				break;

		case 8 : 
				bin[n*4]   = 1;
			   	bin[n*4+1] = 0;
				bin[n*4+2] = 0;	
				bin[n*4+3] = 0;
				break;

		case 9 : 	
				bin[n*4]   = 1;
			   	bin[n*4+1] = 0;
				bin[n*4+2] = 0;	
				bin[n*4+3] = 1;
				break;

		case 10 : 	
				bin[n*4]   = 1;
			   	bin[n*4+1] = 0;
				bin[n*4+2] = 1;	
				bin[n*4+3] = 0;
				break;

		case 11 : 	
				bin[n*4]   = 1;
			   	bin[n*4+1] = 0;
				bin[n*4+2] = 1;	
				bin[n*4+3] = 1;
				break;

		case 12 : 	
				bin[n*4]   = 1;
			   	bin[n*4+1] = 1;
				bin[n*4+2] = 0;	
				bin[n*4+3] = 0;
				break;

		case 13 : 	
				bin[n*4]   = 1;
			   	bin[n*4+1] = 1;
				bin[n*4+2] = 0;	
				bin[n*4+3] = 1;
				break;

		case 14 : 	
				bin[n*4]   = 1;
			   	bin[n*4+1] = 1;
				bin[n*4+2] = 1;	
				bin[n*4+3] = 0;
				break;

		case 15 : 	
				bin[n*4]   = 1;
			   	bin[n*4+1] = 1;
				bin[n*4+2] = 1;	
				bin[n*4+3] = 1;
				break;

		default :
				cout <<"Error detected!\n\n";
			
		}
		
	}

	output.push_back(bin[15]);
	output.push_back(bin[6]);
	output.push_back(bin[19]);
	output.push_back(bin[20]);

	output.push_back(bin[28]);
	output.push_back(bin[11]);
	output.push_back(bin[27]);	
	output.push_back(bin[16]);
	
	output.push_back(bin[0]);	
	output.push_back(bin[14]);	
	output.push_back(bin[22]);	
	output.push_back(bin[25]);
	
	output.push_back(bin[4]);	
	output.push_back(bin[17]);
	output.push_back(bin[30]);
	output.push_back(bin[9]);
	
	output.push_back(bin[1]);	
	output.push_back(bin[7]);	
	output.push_back(bin[23]);	
	output.push_back(bin[13]);	
	
	output.push_back(bin[31]);	
	output.push_back(bin[26]);	
	output.push_back(bin[2]);	
	output.push_back(bin[8]);
	
	output.push_back(bin[18]);	
	output.push_back(bin[12]);	
	output.push_back(bin[29]);	
	output.push_back(bin[5]);
	
	output.push_back(bin[21]);	
	output.push_back(bin[10]);	
	output.push_back(bin[3]);	
	output.push_back(bin[24]);
	return output;
}//Func_f	

//---------------------R_Finder--------------------------------------
binVector R_Finder(binVector L, binVector R, binVector k, int n){
   binVector tmp;
   tmp=Lists_Plus(L,Func_f(Lists_Plus(List_Extender(R),k)));

   //cout << "     R" << n << " is:\n";
   
   //Print_List(tmp);
   
   return tmp;
}//R_Finder
   
//-------------------Lists_Merger------------------------------------
binVector Lists_Merger(binVector F, binVector L){// append L to F
   binVector tmp;
      for(int i=1; i<=F.size(); i++)
         tmp.push_back(F[i-1]);
      for(int j=1; j<=L.size(); j++)
         tmp.push_back(L[j-1]);   
   return tmp;
}//Lists_Merger

//--------------------InvIP------------------------------------------
binVector InvIP(binVector C){// perform inverse permutation of IP
   binVector tmp;
   for(int i=1; i<=8; i++){
      tmp.push_back(C[40-i]);
      tmp.push_back(C[8-i]);
      tmp.push_back(C[48-i]);
      tmp.push_back(C[16-i]);
      tmp.push_back(C[56-i]);
      tmp.push_back(C[24-i]);
      tmp.push_back(C[64-i]);
      tmp.push_back(C[32-i]);
   }
   return tmp;
}//InvIP

//===================================================================