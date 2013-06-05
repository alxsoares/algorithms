package alex.datastructures;

import java.util.Stack;

public class SortingStack {

    public static Stack<Integer> sortDecreasing(final Stack<Integer> s) {
        Stack<Integer> r = new Stack<Integer>();
        while (!s.isEmpty()) {
            int temp = s.pop();
            while (!r.isEmpty() && r.peek() > temp) {
                s.push(r.pop());
            }
            r.push(temp);
        }
        return r;
    }
    
    public static Stack<Integer> sortIncreasing(Stack<Integer> s){
    	Stack<Integer> r = new Stack<>();
    	while(!s.isEmpty()){
    		int temp = s.pop();
    		while(!r.isEmpty()&& r.peek() < temp){
    			s.push(r.pop());
    		}
    		r.push(temp);
    	}
    	return r;
    }
    public static Stack<Integer> sort(Stack<Integer> s){
    	Stack<Integer> res = new Stack<>();
    	if(s==null || s.isEmpty()) return res;
    	while(!s.isEmpty()){
    		int el = s.pop();
    		while(!res.isEmpty() && res.peek()< el){
    			s.push(res.pop());
    		}
    		res.push(el);
    	}
    	return res;
    }
    public static void insertSorted(Stack<Integer> s, int el){
    	if(s.isEmpty() || s.peek() > el){
    		s.push(el);
    		return;
    	}
    	int smaller = s.pop();
    	insertSorted(s, el);
    	s.push(smaller);
    }
    public static void main(final String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        s.push(3000);
        s.push(13);
        s.push(1);
        s.push(0);
        s.push(111);
        s.push(11);
        Stack<Integer> r = sortIncreasing(s);
        while (!r.isEmpty()) {
            System.out.printf("%d ", r.pop());
        }
        System.out.println();
        s.push(3000);
        s.push(13);
        s.push(1);
        s.push(0);
        s.push(111);
        s.push(11);
        r = sortDecreasing(s);
        while (!r.isEmpty()) {
            System.out.printf("%d ", r.pop());
        }
        System.out.println();
        s.push(3000);
        s.push(13);
        s.push(-11);
        s.push(1);
        s.push(0);
        s.push(111);
        s.push(11);
        Stack<Integer> sorted = sort(s);
        insertSorted(sorted, -10);
        insertSorted(sorted, -20);
        insertSorted(sorted, 4000);
        while(!sorted.isEmpty()){
        	System.out.printf("%d ", sorted.pop());
        }
        System.out.println();
    }
}
