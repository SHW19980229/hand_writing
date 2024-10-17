package com.yss;

public class MyHashMap<K,V> {
    class Node<K,V>{
        K key;
        V val;
        int hash;
        Node<K,V> next;
        public Node(){}
        public Node(int hash,K key,V val,Node<K,V> next){
            this.hash=hash;
            this.key=key;
            this.val=val;
            this.next=next;
        }
    }

    private Node<K,V>[] table;
    private int threshold;
    private int size;
    private double load_factor=0.75;

    private int hash(Object key){
        if(key==null) return 0;
        int h=key.hashCode();
        return h^(h>>>16);
    }
    public MyHashMap(){}

    public V get(K key){
        if(table==null||table.length==0){
            return null;
        }else{
            int h=hash(key),n=table.length;
            int index=h&(n-1);
            Node<K,V> p=table[index];
            if(p==null){
                return null;
            }
            while(p!=null){
                if((p.hash==h)&&((p.key==key)||(p.key.equals(key)))){
                    return p.val;
                }
                p=p.next;
            }
            return null;
        }
    }

    public void put(K key,V val){
        if(table==null||table.length==0){
           table=resize(table);
        }
        putVal(key,val,table);
    }

    private void putVal(K key,V val,Node<K,V>[] tab){
        int h=hash(key),n=tab.length;
        int index=h&(n-1);
        if(tab[index]==null){
            tab[index]=new Node<>(h,key,val,null);
            ++size;
        }else{
            int cnt=1;
            Node<K,V> pre=tab[index],cur=pre.next;
            while(cur!=null){
                ++cnt;
                if(cur.hash==h&&((cur.key==key)||(cur.key.equals(key)))){
                    cur.val=val;
                    break;
                }
                pre=cur;
                cur=cur.next;
            }
            if(cur==null||pre.next==null){
                pre.next=new Node<>(h,key,val,null);
                ++size;
                ++cnt;
            }
            if(cnt==8){
                tab=resize(tab);
            }
        }
        if(size>=threshold){
            tab=resize(tab);
        }
    }

    public V remove(K key){
        if(table==null||table.length==0){
            return null;
        }else{
            int h=hash(key),n=table.length;
            int index=h&(n-1);
            Node<K,V> pre=table[index];
            if(pre==null) return null;
            if(pre.hash==h&&((pre.key==key)||(pre.key.equals(key)))){
                table[index]=pre.next;
                pre.next=null;
                --size;
                return pre.val;
            }
            Node<K,V> cur=pre.next;
            while(cur!=null){
                if(cur.hash==h&&((cur.key==key)||(cur.key.equals(key)))){
                    pre.next=cur.next;
                    cur.next=null;
                    --size;
                    return cur.val;
                }
                pre=cur;
                cur=cur.next;
            }
            return null;
        }
    }

    public Node<K,V>[] resize(Node<K,V>[] tab){
        if(tab==null||tab.length==0){
            threshold=(int)(16*load_factor);
            return new Node[16];
        }else{
            int n=tab.length;
            Node<K,V>[] tabNew=new Node[n<<1];
            size=0;
            threshold=(int)(load_factor*tabNew.length);
            for(int i=0;i<tab.length;++i){
                Node<K,V> p=tab[i];
                while(p!=null){
                    putVal(p.key,p.val,tabNew);
                    p=p.next;
                }
            }
            return tabNew;
        }
    }

}
