//https://leetcode.com/problems/broken-calculator/

//target has to be always even
//make sure target is always even and do this until it is <= startValue
//T.C = log(targetValue)

class Solution {
    public int brokenCalc(int startValue, int target) {
        int count = 0;
        while(target > startValue){
            if((target&1) == 0){
                target = target>>1;
            }
            else target++;
        count++;
        }
        return count+startValue-target;
    }
}
