package com.zuochengyun.alorithm.arithmetic_skill;
@SuppressWarnings({"all"})
public class DesignBitSetAdvanced {
       private int[] set;
       private final int size;
       private int ones;
       private int zeros;
       private boolean reserve;
//    Bitset(int size) Initializes the Bitset with size bits, all of which are 0.
       public DesignBitSetAdvanced(int size){
           set = new int[(size+31)/32];
           this.size = size;
           ones = 0;
           zeros = size;
           reserve = false;
       }
//    void fix(int idx) Updates the value of the bit at the index idx to 1. If the value was already 1, no change occurs.
    public void fix(int idx){
           //如果进行翻转，那么就是对现在idx位上的1变0，因为idx位上应有的变化是0*变1*，但是0*对应现在的1，1*对应现在的0
        //如果没有进行翻转就是正常的0变1
           if(reserve){
               if(((set[idx/32]>>>(idx%32))&1)==1){
                   ones++;
                   zeros--;
                   set[idx/32]&=~(0x00000001<<(idx%32));
               }
           }else {
               if(((set[idx/32]>>>(idx%32))&1)==0){
                   ones++;
                   zeros--;
                   set[idx/32]|=(0x00000001<<(idx%32));
               }
           }
    }
//    void unfix(int idx) Updates the value of the bit at the index idx to 0. If the value was already 0, no change occurs.
    //如果进行过翻转，就是1*变为0*，即0变1，如果没进行过反转就是正常的1变0
    public void unfix(int idx){
           if(reserve){
               if(((set[idx/32]>>>(idx%32))&1)==0){
                   ones--;
                   zeros++;
                   set[idx/32]|=(0x00000001<<(idx%32));
               }
           }else {
               if(((set[idx/32]>>>(idx%32))&1)==1){
                   ones--;
                   zeros++;
                   set[idx/32]&=~(0x00000001<<(idx%32));
               }
           }
    }
//    void flip() Flips the values of each bit in the Bitset. In other words, all bits with value 0 will now have value 1 and vice versa.
    public void flip(){
           reserve=!reserve;
           //zeros=a,ones=b
        //zeros=a^b
           zeros^=ones;
           //ones=b^(a^b)=a
           ones^=zeros;
           //zeros=a^(a^b)=b
           zeros^=ones;

    }
//    boolean all() Checks if the value of each bit in the Bitset is 1. Returns true if it satisfies the condition, false otherwise.
    public boolean all(){
           return ones==size;
    }
//    boolean one() Checks if there is at least one bit in the Bitset with value 1. Returns true if it satisfies the condition, false otherwise.
    public boolean one(){
           return ones!=0;
    }
//    int count() Returns the total number of bits in the Bitset which have value 1.
    public int count(){
           return ones;
    }
//    String toString() Returns the current composition of the Bitset. Note that in the resultant string, the character at the ith index should coincide with the value at the ith bit of the Bitset.
    public String toString(){
           StringBuilder stringBuilder = new StringBuilder();
           String s = new String();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(reserve==true?((set[i/32]>>>(i%32))&1)^1:(set[i/32]>>>(i%32))&1);
        }
        return stringBuilder.toString();
    }
    public static void main(String[] args) {
        DesignBitSetAdvanced designBitSetAdvanced = new DesignBitSetAdvanced(5);
        designBitSetAdvanced.fix(1);
        designBitSetAdvanced.fix(3);
        System.out.println(designBitSetAdvanced);
    }

}
