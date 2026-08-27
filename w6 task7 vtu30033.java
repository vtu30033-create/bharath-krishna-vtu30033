#include <bits/stdc++.h>

using namespace std;

string ltrim(const string &);
string rtrim(const string &);

/*
 * Complete the 'palindromeIndex' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts STRING s as parameter.
 */

int palindromeIndex(string s) {
    int left = 0;
    int right = s.length() - 1;

    while (left < right) {

        if (s[left] != s[right]) {

            // Try removing left character
            int l = left + 1;
            int r = right;
            bool valid = true;

            while (l < r) {
                if (s[l] != s[r]) {
                    valid = false;
                    break;
                }
                l++;
                r--;
            }

            if (valid) {
                return left;
            }

            // Try removing right character
            l = left;
            r = right - 1;
            valid = true;

            while (l < r) {
                if (s[l] != s[r]) {
                    valid = false;
                    break;
                }
                l++;
                r--;
            }

            if (valid) {
                return right;
            }

            return -1;
        }

        left++;
        right--;
    }

    return -1;
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    string q_temp;
    getline(cin, q_temp);

    int q = stoi(ltrim(rtrim(q_temp)));

    for (int q_itr = 0; q_itr < q; q_itr++) {
        string s;
        getline(cin, s);

        int result = palindromeIndex(s);

        fout << result << "\n";
    }

    fout.close();

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
