class Solution {
    List<Integer> res = new ArrayList<>();
    public List<Integer> splitIntoFibonacci(String num) {
        recurse(0, num, res);
        return res;
    }
    
    private boolean recurse(int indx, String num, List<Integer> res){
        int n = num.length();
        if(indx == n && res.size() >=3)return true;
        boolean flag = true;
        int number = 0;
        for(int i = indx;i<n;i++){
            number = number*10 + num.charAt(i) - '0';
            if(number < 0)break;
            if(number == 0)flag = false;
            
            if(res.size() <= 1){
                res.add(number);
                if(recurse(i+1, num, res))
                    return true;
                res.remove(res.size()-1);
            }else if(res.get(res.size()-1) + res.get(res.size()-2) == number){
                res.add(number);
                if(recurse(i+1, num, res))return true;
                res.remove(res.size()-1);
                return false;
            }
            
            if(!flag)break;
        }
        return false;
    }
}
//https://leetcode.com/problems/split-array-into-fibonacci-sequence/
