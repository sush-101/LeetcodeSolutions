//https://leetcode.com/problems/online-stock-span/

//maintain monotonic sequence along with their indices

//Approach1: using stack

class StockSpanner {
    int len;
    Stack<Integer> stack;
    ArrayList<Integer> al;
    public StockSpanner() {
        stack = new Stack<>();
        al = new ArrayList<>();
        len = 0;
    }
    
    public int next(int price) {
        int count = 0;
        while(!stack.isEmpty() && al.get(stack.peek())<=price){
            stack.pop();
        }
        if(stack.isEmpty())count = len;
        else count = len-stack.peek()-1;
        stack.push(len);
        al.add(price);len++;
        return 1+count;
    }
}
//t.c: O(n), space: O(n)

//Approach2: Using prev pointer
class StockSpanner {
    Node prev;
    int index;
    public StockSpanner() {
        index = 0;
        prev = new Node(-1, Integer.MAX_VALUE);
    }
    
    public int next(int price) {
        Node test = prev;
        while(test!=null && test.value <= price){
            test = test.prevGreater;
        }
        Node temp = new Node(index++,price);
        temp.prevGreater = test;
        prev = temp;
        return temp.index-test.index;
    }
}
class Node{
    int value, index;
    Node prevGreater;
    Node(int index, int value){
        this.value = value;this.index = index;
        this.prevGreater = null;
    }
}

//t.c - O(n), s.c - O(n)
