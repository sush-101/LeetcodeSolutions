class Solution {
public:
    int longestValidSubstring(string word, vector<string>& forbidden) {
        int len = 0;
        unordered_set<string> all;
        for (const string &s : forbidden) {
            all.insert(s);
            len = max(len, (int) s.length());
        }
        const int n = word.length();
        int r = 0;
        for (int i = n - 1, right = n; right > r && i >= 0; --i) {
            int now = 0;
            string temp;
            for (int j = i; j < right && j - i < len; ++j) {
                temp.push_back(word[j]);
                if (all.count(temp)) {
                    right = j;
                    break;
                }
            }
            r = max(r, right - i);
        }
        return r;
        
    }
};