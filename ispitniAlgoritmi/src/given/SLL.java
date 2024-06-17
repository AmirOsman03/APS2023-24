package given;

public class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int size() {
        int listSize = 0;
        SLLNode<E> tmp = first;
        while (tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp.element;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += " -> " + tmp.element;
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, null);
        ins.succ = first;
        //SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == before) {
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                tmp.succ = new SLLNode<E>(o, before);
                ;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = new SLLNode<E>(o, null);
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return null;
    }

    public void merge(SLL<E> in) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = in.getFirst();
        } else {
            first = in.getFirst();
        }
    }

    //MIRROR OR SWAP
    public void mirror() {
        if (first != null) {
            //m=nextsucc, p=tmp,q=next
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while (tmp != null) {
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }
    }

    //MIDDLE ELEMENT OF A LINKED LIST
    public SLLNode<E> findMiddleElement(SLL<E> list) {
        SLLNode<E> fast = list.getFirst();
        SLLNode<E> slow = list.getFirst();

        while (fast != null && fast.succ != null) {
            slow = slow.succ;
            fast = fast.succ.succ;
        }

        return slow;
    }

    //REMOVE MIDDLE ELEMENT OF A LINKED LIST
    public SLL<E> removeMiddleElement(SLL<E> list) {
        SLLNode<E> fast = list.getFirst();
        SLLNode<E> slow = list.getFirst();

        while (fast != null && fast.succ != null) {
            slow = slow.succ;
            fast = fast.succ.succ;
        }

        delete(slow);

        return list;
    }

    //REMOVE DUPLICATES FROM A LINKED LIST
    public SLL<E> removeDuplicates(SLL<E> list) {
        SLLNode<E> curr = list.first;

        while (curr != null && curr.succ != null) {
            if (curr.element == curr.succ.element) {
                curr.succ = curr.succ.succ;
            }
            curr = curr.succ;
        }

        return list;
    }

    //REMOVE ALL DUPLICATES FROM A LINKED LIST
    public SLL<E> removeAllDuplicates(SLL<E> list) {
        SLLNode<E> curr = list.getFirst();

        while (curr != null) {
            SLLNode<E> runner = curr;
            while (runner.succ != null) {   //da odi se dodeka ne stigne na kraj
                if (runner.succ.element.equals(curr.element)) {
                    runner.succ = runner.succ.succ;
                } else {
                    runner = runner.succ;
                }
            }
            curr = curr.succ;
        }

        return list;
    }

    //MERGE NODES BETWEEN ZEROS IN LINKED LIST
    public SLL<E> mergeBetweenZeros(SLL<E> list) {
        SLLNode<E> curr = list.getFirst();
        SLLNode<E> prevZero = null;

        while (curr != null) {
            int sum = 0;
            SLLNode<E> startSum = curr;

            while (curr != null && !curr.element.equals(0)) {
                sum += (Integer) curr.element;
                curr = curr.succ;
            }

            if (curr != null) { //curr is in zero
                curr = curr.succ;
            }

            if (prevZero != null) {
                prevZero.succ = startSum;
            }

            if (sum > 0) {
                startSum.element = (E) (Integer) sum;   // Set the sum at the start
                startSum.succ = curr;   // Point to the next zero or end of list
                prevZero = startSum;    // Update the last zero pointer to the new sum node
            } else if (prevZero != null) {
                prevZero.succ = curr;   // If sum is zero, skip the segment
            }

            while (curr != null && curr.element.equals(0)) {
                prevZero = curr;
                curr = curr.succ;
            }
        }

        while (first != null && first.element.equals(0)) {
            first = first.succ;
        }

        return this;
    }

    //MERGE IN BETWEEN NODES IN LINKED LIST
    public SLL<E> mergeInBetween(SLL<E> list1, int a, int b, SLL<E> list2) {
        SLLNode<E> curr = list1.getFirst();
        SLLNode<E> first = list1.first;

        for (int i = 0; i < b; i++) { // odi do b
            if (i == a - 1) {
                first = curr;
            }
            curr = curr.succ;
        }

        SLLNode<E> second = first.succ; //(b + 1)
        SLLNode<E> curr2 = list2.getFirst();
        first.succ = list2.first; //povrzi go elementot na list1 so list2

        while (curr2.succ != null) {
            curr2 = curr2.succ; //izlistaj ja list2
        }
        curr2.succ = curr.succ; //povrzi ja list2 so posledniot element na list1

        return list1;
    }

    //MAXIMUM TWIN SUM OF LINKED LIST
    public int maxTwinSum(SLL<E> list) {
        SLLNode<E> slow = list.getFirst();
        SLLNode<E> fast = list.getFirst();
        SLLNode<E> prev = null;

        //ja naogjame sredinata prvo posle pravime swap
        while (fast != null && fast.succ != null) {
            fast = fast.succ.succ;
            SLLNode<E> tmp = slow.succ;
            slow.succ = prev;
            prev = slow;
            slow = tmp;
        }

        int sum = 0;
        while (slow != null) {
            sum = Math.max(sum, (Integer) prev.element + (Integer) slow.element);
            prev = prev.succ;   //ne zaboravame da go dvizime prev
            slow = slow.succ;   //ne zaboravame da go dvizime slow
        }

        return sum;
    }

    //REMOVE GREATER NODES IN LINKED LIST
    public SLL<E> removeGreater(SLL<E> list) {
        if (first == null || first.succ == null) {
            return this; // If the list is empty or has only one node, return it as is.
        }

        // Reverse the linked list
        list.mirror();
        SLLNode<E> curr = first;
        SLLNode<E> prev = null;

        SLLNode<E> maxNode = first;
        while (curr != null && curr.succ != null) {
            if ((Integer) curr.succ.element < (Integer) maxNode.element) {
                curr.succ = curr.succ.succ; //izbrisi gi site elementi sto se pomali od max
            } else {
                curr = curr.succ;
                if ((Integer) curr.element > (Integer) maxNode.element) {
                    maxNode = curr;
                }
            }
        }

        // Reverse the linked list back to its original order
        list.mirror();

        return this;
    }

    public SLL<E> swapNodesInPairs(SLL<E> list) {
        SLLNode<E> curr = list.getFirst();
        while (curr != null && curr.succ != null) {
            // Swap the elements of the current node and the next node
            E temp = curr.element;
            curr.element = curr.succ.element;
            curr.succ.element = temp;

            // Move to the next pair
            curr = curr.succ.succ;
        }
        return list;
    }

    /*
    ZIG ZAG POZITIVNI SO NEGATIVNI
    input: 4 -> -3 -> -6 -> 0 -> 7 -> 7 -> -2 -> 5
    output: 4 -> -3 -> 3 -> -6 -> 7 -> -2 -> 5
    */
    public void makeZigZag (SLL<E> list) {
        SLLNode<E> curr = list.getFirst();
        SLLNode<E> prev = null;

        while (curr != null && curr.succ != null) {
            SLLNode<E> next = curr.succ;

            int currVal = (int) curr.element;
            int nextVal = (int) next.element;

            if (currVal > 0 && nextVal > 0) {
                delete(next); // se brise vtoriot
            } else if (currVal < 0 && nextVal < 0) {
                insertAfter((E) (Integer) Math.abs(currVal), curr);
            } else if (nextVal == 0) {
                delete(next); // ako vtoriot e 0, se brise
            } else { // dokolku dvata elementi se 0
                prev = curr;
                curr = curr.succ;
            }
        }
    }

    /*
    PUT WORDS TOGETHER
    input: c -> a -> t -> , -> d -> o -> g -> , -> c -> o -> w
    output: cat -> dog -> cow
    */
    public void putWordsTogether(SLL<String> list) {
        SLLNode<String> curr = list.getFirst();
        SLL<String> newList = new SLL<>();
        StringBuilder sb = new StringBuilder();

        while (curr != null) {
            if (!curr.element.equals(",")) {
                sb.append(curr.element);
            }
            if (curr.element.equals(",") || curr.succ == null) {
                newList.insertLast(sb.toString()); // da se ispecati vo novata lista
                sb.setLength(0);
            }

            curr = curr.succ;
        }

        this.first = (SLLNode<E>) newList.first;
    }

    /*
    IZBRISI PAREN BROJ POVTORUVANJE NA BROJ
    input: 1 -> 2 -> 5 -> 7 -> 9 -> 12, a = 7
    output: 1 -> 2 -> 5 -> 9 -> 12
    */
    public void izbrisiParenBrojPati (SLL<Integer> list, int a) {
        SLLNode<Integer> curr = list.getFirst();
        SLLNode<Integer> posleden = null;
        int ct = 0;

        while (curr != null && curr.succ != null) {
            if (curr.element == a) {
                ct++;
                posleden = curr;
            }
            curr = curr.succ;
        }
        if (ct % 2 == 1) {
            delete((SLLNode<E>) posleden);
        }
    }

    //BRISENJE NA POSLEDNO POJAVUVANJE
    public void izbrisiPoslednoPojavuvanje (SLL<Integer> list, int a) {
        SLLNode<Integer> curr = list.getFirst();
        SLLNode<Integer> posleden = null;

        while (curr != null) {
            if (curr.element == a) {
                posleden = curr;
            }
            curr = curr.succ;
        }
        delete((SLLNode<E>) posleden);
    }

    /*
    ALTERNATIVE MERGE (vo nova lista da se dodavaat dva elementi od list1, pa posle dva elementi od list2)
    Input: list1 = [ 5 -> 7 -> 9 ],
    list2 = [ 1 -> 1 -> 4 -> 5 -> 6 -> 8 -> 9 -> 4 ]
    Output: 5 -> 7 -> 1 -> 1 -> 9 -> 4 -> 5 -> 6 -> 8 -> 9 -> 4
     */
    public SLL<Integer> alternativeMerge (SLL<Integer> list1, SLL<Integer> list2) {
        SLLNode<Integer> curr1 = list1.first;
        SLLNode<Integer> curr2 = list2.first;
        SLL<Integer> res = new SLL<>();

        while (curr1 != null || curr2 != null) {
            int m = 2;
            for (int i=0;i<m && curr1 != null;i++) {
                res.insertLast(curr1.element);
                curr1 = curr1.succ;
            }
            int n = 2;
            for (int i=0;i<n && curr2 != null;i++) {
                res.insertLast(curr2.element);
                curr2 = curr2.succ;
            }
        }

        return res;
    }

}

