class Solution {
  
    /*
      Time : O(N)
      Space : O(N)
      Leetcode : YES
    */
  
    /*
      Approach :
      Greedy + stack
       1. to minimise the value of a number we need to remove largest numbers from LHS
          "1432219", k = 3
          4 3 2 remove them --> out : 1219
          So For each item from left find the largest number in the right and remove that.
          removing 1 doesn't make sense since, removing 4 would lead us to lowest number.
       2. Use stack to keep track of numbers in left, as you iterate find the next largest number and remove that.
       3. lastly whatever is left in the stack reverse it and return.
       
       edge cases 
       1. '1111', k = 2 
                 we won't remove anything so k will still be 2, in this case pop() from the stack until k = 0, so out : 11
       2. '10200', k = 1
                If we remove 2( largest number) then output will be 1000, but if we remove, 1 we will be left with 0200 so 200 is lowest.
                while returning a string we have to return string without leading zeros. so run for loop to remove leading zeros, 
    */
    public String removeKdigits(String num, int k) {
        if(num == null || num.length() == 0) return "";
        
        int n = num.length();
        if(k == n) return "0";
        Stack<Integer> stk = new Stack<>();
        int removedTillNow = 0;
        
        for(int i = 0 ; i < n; i++){
            char c = num.charAt(i);
            int cur = c - '0';
            
            while(!stk.isEmpty() && stk.peek() > cur && removedTillNow < k){
                stk.pop();
                removedTillNow++;
            }
                
            stk.push(cur);
        }
        
        while(removedTillNow < k){
            stk.pop();
            removedTillNow++;            
        }
        
        StringBuilder builder = new StringBuilder();
        
        while(!stk.isEmpty()){
            builder.append(stk.pop());
        }
        
        builder = builder.reverse();
        
        
       while(builder.length()>1 && builder.charAt(0)=='0')
            builder.deleteCharAt(0);
        
        return builder.length() == 0 ? "0" : builder.toString();
    }
}
