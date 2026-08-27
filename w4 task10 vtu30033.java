#include <bits/stdc++.h>
using namespace std;

string ltrim(const string &);
string rtrim(const string &);
vector<string> split(const string &);

/*
 * Complete the 'matrixRotation' function below.
 *
 * The function accepts:
 *  1. 2D_INTEGER_ARRAY matrix
 *  2. INTEGER r
 */

void matrixRotation(vector<vector<int>> matrix, int r) {
    int m = matrix.size();
    int n = matrix[0].size();

    int layers = min(m, n) / 2;

    for (int layer = 0; layer < layers; layer++) {
        int top = layer;
        int left = layer;
        int bottom = m - layer - 1;
        int right = n - layer - 1;

        vector<int> ring;

        // Top row
        for (int j = left; j <= right; j++) {
            ring.push_back(matrix[top][j]);
        }

        // Right column
        for (int i = top + 1; i <= bottom; i++) {
            ring.push_back(matrix[i][right]);
        }

        // Bottom row
        for (int j = right - 1; j >= left; j--) {
            ring.push_back(matrix[bottom][j]);
        }

        // Left column
        for (int i = bottom - 1; i > top; i--) {
            ring.push_back(matrix[i][left]);
        }

        int len = ring.size();
        int shift = r % len;

        vector<int> rotated(len);

        // Anti-clockwise rotation
        for (int i = 0; i < len; i++) {
            rotated[i] = ring[(i + shift) % len];
        }

        int index = 0;

        // Top row
        for (int j = left; j <= right; j++) {
            matrix[top][j] = rotated[index++];
        }

        // Right column
        for (int i = top + 1; i <= bottom; i++) {
            matrix[i][right] = rotated[index++];
        }

        // Bottom row
        for (int j = right - 1; j >= left; j--) {
            matrix[bottom][j] = rotated[index++];
        }

        // Left column
        for (int i = bottom - 1; i > top; i--) {
            matrix[i][left] = rotated[index++];
        }
    }

    // Print the matrix
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            cout << matrix[i][j];

            if (j != n - 1) {
                cout << " ";
            }
        }
        cout << "\n";
    }
}

int main()
{
    string first_multiple_input_temp;
    getline(cin, first_multiple_input_temp);

    vector<string> first_multiple_input =
        split(rtrim(first_multiple_input_temp));

    int m = stoi(first_multiple_input[0]);
    int n = stoi(first_multiple_input[1]);
    int r = stoi(first_multiple_input[2]);

    vector<vector<int>> matrix(m, vector<int>(n));

    for (int i = 0; i < m; i++) {
        string matrix_row_temp_temp;
        getline(cin, matrix_row_temp_temp);

        vector<string> matrix_row_temp =
            split(rtrim(matrix_row_temp_temp));

        for (int j = 0; j < n; j++) {
            matrix[i][j] = stoi(matrix_row_temp[j]);
        }
    }

    matrixRotation(matrix, r);

    return 0;
}

string ltrim(const string &str) {
    string s(str);

    s.erase(
        s.begin(),
        find_if(s.begin(), s.end(),
                [](unsigned char ch) {
                    return !isspace(ch);
                })
    );

    return s;
}

string rtrim(const string &str) {
    string s(str);

    s.erase(
        find_if(s.rbegin(), s.rend(),
                [](unsigned char ch) {
                    return !isspace(ch);
                }).base(),
        s.end()
    );

    return s;
}

vector<string> split(const string &str) {
    vector<string> tokens;
    string token;
    stringstream ss(str);

    while (ss >> token) {
        tokens.push_back(token);
    }

    return tokens;
}
