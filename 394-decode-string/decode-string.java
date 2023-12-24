class Solution {
    public String decodeString(String s) {
        Stack<String> stack = new Stack<>();
        for(char x: s.toCharArray()){
            if(x == ']'){
                StringBuilder sb = new StringBuilder();
                while(!stack.isEmpty() && !stack.peek().equals("[")){
                    sb.insert(0,stack.pop());
                }
                stack.pop();
                StringBuilder num = new StringBuilder();
                while(!stack.isEmpty() && stack.peek().matches("^-?\\d+$")){
                    num.insert(0,Integer.parseInt(stack.pop()));
                }
                stack.push(expandString(sb.toString(), Integer.parseInt(num.toString())));
            }else{
                stack.push(x+"");
            }
        }
        StringBuilder res = new StringBuilder();
        while(!stack.isEmpty()){
            res.insert(0,stack.pop());
        }
        return res.toString();
    }
    public String expandString(String str, int freq){
        System.out.println("str "+str+" freq "+freq);
        StringBuilder sb = new StringBuilder();
        while(freq--!=0){
            sb.append(str);
        }
        return sb.toString();
    }
}