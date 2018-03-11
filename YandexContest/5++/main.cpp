#include <iostream>

using namespace std;

int main()
{
    long long k, m, d;
    cin >> k >> m >> d;

    long long a = 0;

    while (m + 5*k - 7*(a+4) >= 0)
    {
        m += 5*k - 7*(a+4);
        a += 7;
    }

    while(true)
    {
        m += (d < 5 ? k : 0) - (a + 1);
        if (m >= 0)
        {
            a++;
            d++;
            d %= 7;
        }
        else
        {
            break;
        }
    }

    cout << a;
    return 0;
}
