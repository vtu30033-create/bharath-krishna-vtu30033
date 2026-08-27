#include <bits/stdc++.h>
using namespace std;

string ltrim(const string &);
string rtrim(const string &);

const int MAXN = 500005;
const int MAXX = 1000010;

char S[MAXX];

int R[2][MAXX];
int T[2][MAXX];

int tree[1 << 21];

int n;
int leaves;

/*
 * Manacher algorithm
 *
 * parity = 0 -> odd length palindromes
 * parity = 1 -> even length palindromes
 */
void manacher(int length, int parity) {
    int *table = R[parity];

    int i = 0;
    int j = 0;

    while (i < length) {
        int k;

        while (i - j >= 0 &&
               i + j + parity < length &&
               S[i - j] == S[i + j + parity]) {
            j++;
        }

        table[i] = j;

        for (k = 1;
             k < j && table[i - k] != table[i] - k;
             k++) {

            table[i + k] =
                min(table[i - k], table[i] - k);
        }

        i += k;
        j = max(j - k, 0);
    }
}

/*
 * Range maximum update
 */
void updateTree(int node, int left, int right,
                int ql, int qr, int value) {

    if (ql > right || qr < left) {
        return;
    }

    if (ql <= left && right <= qr) {
        tree[node] = max(tree[node], value);
        return;
    }

    int mid = (left + right) / 2;

    updateTree(node * 2,
               left,
               mid,
               ql,
               qr,
               value);

    updateTree(node * 2 + 1,
               mid + 1,
               right,
               ql,
               qr,
               value);
}

/*
 * Process one palindrome center
 */
void processCenter(int pos, int parity) {

    int &ptr = R[parity][pos];

    int diff =
        (ptr << 1) - (!parity) - n;

    if (diff > 0) {
        diff += (diff & 1);
        ptr -= diff >> 1;
    }

    int left =
        pos - ptr + 1;

    int right =
        pos + ptr - (parity == 0);

    int length =
        (ptr << 1) - (!parity);

    if ((parity == 0 && ptr > 1) ||
        (parity == 1 && ptr > 0)) {

        T[0][left] =
            max(T[0][left], length);

        T[1][right] =
            max(T[1][right], length);

        int ql =
            max(0, right - n + 1);

        int qr =
            min(n - 1, left);

        if (ql <= qr) {
            updateTree(
                1,
                0,
                leaves - 1,
                ql,
                qr,
                length
            );
        }
    }
}

/*
 * Process all palindrome centers
 */
void processPalindromes(int length) {

    for (int i = 0; i < length; i++) {

        // Odd palindrome
        processCenter(i, 0);

        // Even palindrome
        processCenter(i, 1);
    }
}

/*
 * Query maximum value at one position
 */
int queryTree(int pos) {

    int result = 1;

    int node = pos + leaves;

    while (node > 0) {
        result = max(result, tree[node]);
        node /= 2;
    }

    return result;
}

void initTree(int size) {

    leaves = 1;

    while (leaves < size) {
        leaves <<= 1;
    }

    for (int i = 0; i < 2 * leaves; i++) {
        tree[i] = 0;
    }
}

/*
 * Complete the 'circularPalindromes' function below.
 */
vector<int> circularPalindromes(string s) {

    n = s.length();

    /*
     * Duplicate the string.
     *
     * We need 2*n - 1 characters.
     */
    for (int i = 0; i < n; i++) {
        S[i] = s[i];
    }

    for (int i = 0; i < n - 1; i++) {
        S[n + i] = s[i];
    }

    int length = 2 * n - 1;

    initTree(n);

    /*
     * Find all odd palindromes
     */
    manacher(length, 0);

    /*
     * Find all even palindromes
     */
    manacher(length, 1);

    /*
     * Process palindrome information
     */
    processPalindromes(length);

    /*
     * Propagate decreasing palindrome lengths.
     *
     * T[0] handles one side.
     * T[1] handles the other side.
     */
    for (int i = 1; i < length; i++) {

        T[0][i] =
            max(T[0][i],
                T[0][i - 1] - 2);
    }

    for (int i = 1; i < length; i++) {

        T[1][length - i - 1] =
            max(T[1][length - i - 1],
                T[1][length - i] - 2);
    }

    vector<int> answer(n);

    for (int i = 0; i < n; i++) {

        int result = queryTree(i);

        result =
            max(result, T[0][i]);

        result =
            max(result, T[1][i + n - 1]);

        answer[i] = result;
    }

    return answer;
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    string n_temp;
    getline(cin, n_temp);

    int N = stoi(ltrim(rtrim(n_temp)));

    string s;
    getline(cin, s);

    vector<int> result =
        circularPalindromes(s);

    for (size_t i = 0; i < result.size(); i++) {

        fout << result[i];

        if (i != result.size() - 1) {
            fout << "\n";
        }
    }

    fout << "\n";

    fout.close();

    return 0;
}

string ltrim(const string &str) {

    string s(str);

    s.erase(
        s.begin(),
        find_if(
            s.begin(),
            s.end(),
            [](unsigned char ch) {
                return !isspace(ch);
            }
        )
    );

    return s;
}

string rtrim(const string &str) {

    string s(str);

    s.erase(
        find_if(
            s.rbegin(),
            s.rend(),
            [](unsigned char ch) {
                return !isspace(ch);
            }
        ).base(),
        s.end()
    );

    return s;
}
