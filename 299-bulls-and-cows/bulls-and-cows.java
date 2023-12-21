class Solution {
    public String getHint(String secret, String guess) {
        Map<Character, Integer> s = new HashMap<>();
        Map<Character, Integer> g = new HashMap<>();
        int bulls = 0, cows = 0;
        for(int i=0;i<guess.length();i++){
            if(secret.charAt(i) == guess.charAt(i)){
                bulls++;
                continue;
            }
            s.put(secret.charAt(i), s.getOrDefault(secret.charAt(i), 0) + 1);
            g.put(guess.charAt(i), g.getOrDefault(guess.charAt(i), 0) + 1);
        }
        for(Map.Entry<Character, Integer> entry: s.entrySet()){
            cows += Math.min(entry.getValue(), g.get(entry.getKey()) == null ? 0: g.get(entry.getKey()));
        }
        return bulls+"A"+cows+"B";
    }
}