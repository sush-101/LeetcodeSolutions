 class Solution {
    public boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
        int closest_x, closest_y;
        
        if((x1<=x_center && x_center<=x2) || (x2<=x_center && x_center<=x1)){
            closest_x = x_center;
        }else closest_x = Math.abs(x_center-x1)<Math.abs(x_center-x2)?x1:x2;
        
        if((y1<=y_center && y_center<=y2)||(y2<=y_center && y_center<=y1)){
            closest_y = y_center;
        }else closest_y = Math.abs(y_center-y1)<Math.abs(y_center-y2)?y1:y2;
        
        return (closest_y-y_center)*(closest_y-y_center) + (closest_x-x_center)*(closest_x-x_center)<=radius*radius;
    }
}
//https://leetcode.com/problems/circle-and-rectangle-overlapping/
