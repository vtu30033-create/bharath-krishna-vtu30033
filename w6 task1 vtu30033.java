#include <bits/stdc++.h>
using namespace std;

string ltrim(const string &);
string rtrim(const string &);

/*
 * Complete the 'stringSimilarity' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts STRING s as parameter.
 */

int stringSimilarity(string s) {
    int n = s.length();

    vector<int> z(n, 0);

    int left = 0;
    int right = 0;

    for (int i = 1; i < n; i++) {

        if (i <= right) {
            z[i] = min(right - i + 1, z[i - left]);
        }

        while (i + z[i] < n &&
               s[z[i]] == s[i + z[i]]) {
            z[i]++;
        }

        if (i + z[i] - 1 > right) {
            left = i;
            right = i + z[i] - 1;
        }
    }

    int result = 0;

    // Similarity of string with itself
    result = n;

    // Similarity with every suffix
    for (int i = 1; i < n; i++) {
        result += z[i];
    }

    return result;
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    string t_temp;
    getline(cin, t_temp);

    int t = stoi(ltrim(rtrim(t_temp)));

    for (int t_itr = 0; t_itr < t; t_itr++) {
        string s;
        getline(cin, s);

        int result = stringSimilarity(s);

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
