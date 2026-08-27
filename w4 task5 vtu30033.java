#include <bits/stdc++.h>
using namespace std;

/*
 * Complete the 'timeConversion' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts STRING s as parameter.
 */

string timeConversion(string s) {
    string period = s.substr(8, 2);  // AM or PM
    int hour = stoi(s.substr(0, 2));

    if (period == "AM") {
        if (hour == 12) {
            s.replace(0, 2, "00");
        }
    } 
    else { // PM
        if (hour != 12) {
            hour += 12;
            s.replace(0, 2, to_string(hour));
        }
    }

    s.erase(8, 2); // Remove AM/PM
    return s;
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    string s;
    getline(cin, s);

    string result = timeConversion(s);

    fout << result << "\n";

    fout.close();

    return 0;
}
