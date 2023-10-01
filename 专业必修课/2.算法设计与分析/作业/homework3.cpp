#include <iostream>
#include <cmath>
#include <vector>
int F[3][10];
int S[3][10];
using namespace std;
int main()
{
    int G[3][4] = {{2, 4, 7, 11}, {5, 10, 16, 20}, {8, 12, 17, 22}};
    memset(F, 0, sizeof(F));
    memset(S, 0, sizeof(S));
    for (int i = 0; i < 11; i++)
    {
        F[0][i] = G[0][(int)sqrt(i)];
    }
    for (int i = 1; i < 3; ++i)
    {
        for (int j = 0; j < 11; j++)
        {
            int max = 0;
            int index = 0;
            for (int k = 0; k <= (int)sqrt(j); k++)
            {
                if (F[i - 1][j - k * k] + G[i][k] > max)
                {
                    max = F[i - 1][j - k * k] + G[i][k];
                    index = k;
                }
            }
            F[i][j] = max;
            S[i][j] = index;
        }
    }
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 13; j++)
        {
            cout << F[i][j] << " ";
        }
        cout << endl;
    }
    return 0;
}