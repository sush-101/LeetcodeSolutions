// https://leetcode.com/problems/vowel-spellchecker/



class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        int len = queries.length;
        String res[] = new String[len];
        Set<String> exactmatch = new HashSet<>();
        Map<String,String> caseinsensitive = new HashMap<>();
        Map<String,String> vowelcaseinsensitive = new HashMap<>();
        
        for(String x:wordlist){
            exactmatch.add(x);
            String lower =  x.toLowerCase();
            if(!caseinsensitive.containsKey(lower))caseinsensitive.put(lower,x);
            String vowelsRemoved = removeVowels(lower);
            if(!vowelcaseinsensitive.containsKey(vowelsRemoved))
                vowelcaseinsensitive.put(vowelsRemoved,x);   
        }
        
        for(int i=0;i<len;i++){
            String lowerCase = queries[i].toLowerCase(), vowelsRemoved = removeVowels(lowerCase);
            if(exactmatch.contains(queries[i])){
                res[i] = queries[i];
            }else if(caseinsensitive.containsKey(lowerCase)){
                res[i] = caseinsensitive.get(lowerCase);
            }else if(vowelcaseinsensitive.containsKey(vowelsRemoved)){
                res[i] = vowelcaseinsensitive.get(vowelsRemoved);
            }else res[i]="";
        }
        return res;
    }
    public String removeVowels(String x){
        StringBuilder str = new StringBuilder();
        for(char a:x.toCharArray()){
            if(isVowel(a))str.append(".");
            else str.append(a);
        }
        return str.toString();
    }
    public boolean isVowel(char x){
        return x=='a'||x=='e'||x=='i'||x=='o'||x=='u';
    }
}

/* T.C.
preprocessing entire data(toLowerCase and removeVowels) -> 2*( (sum of lengths of words in queries array) + ( sum of lengths of words in wordlist))
                                                        -> (number of words in queries and wordlist) * (max size of a word)
                                                        -> O(n) where n is #chars present in queries and wordlist arrays.
Retrieving from hashmap and hashset-> #queries*3*O(1)
T.C = O(m+n) where m is #queries and n is total #chars present in queries and wordlist arrays combined.


Space: size of wordlist
*/


//Inefficient

class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        int len = queries.length;
        String res[] = new String[len];
        for(int i=0;i<len;i++){
            res[i] = correct(wordlist,queries[i]);
        }
        return res;
    }
    public String correct(String[] wordlist, String word){
        String res = "";
        boolean foundCapitalization = false;
        int len = word.length();
        outer:for(String x:wordlist){
            if(x.length() != len)continue;
            
            boolean capitalization = true, exactMatch = true;
           
            //int count=0;
            for(int i = 0; i < x.length(); i++){
                char a = x.charAt(i), b = word.charAt(i);

                if(a==b){
                    //count++;
                }else if(isCap(a,b)){
                    //count++;
                    exactMatch=false;
                }else if(isVowel(a) && isVowel(b)){
                    //count++;
                    exactMatch=false;
                    capitalization=false;
                }else continue outer;
            }
            if(true){
                if(exactMatch)return x;

                if(capitalization){
                    if(!foundCapitalization){ res = x; foundCapitalization = true;}
                }else if(res == "")res = x;
            }
        }
        return res;
    }
    public boolean isVowel(char x){
        return x=='a'||x=='e'||x=='i'||x=='o'||x=='u'||
            x=='A'||x=='E'||x=='I'||x=='O'||x=='U';
    }
    public boolean isCap(char a, char b){
        return a+32 == b || a-32 == b;
    }
}

/* T.C: #queries*#words_in_wordlist*word_size
      -> O(m*n*K)
Space: O(1)
/*
