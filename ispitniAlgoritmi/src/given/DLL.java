package given;

public class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (after == last) {
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        } else
            return null;
    }

    public E delete(DLLNode<E> node) {
        if (node == first) {
            return deleteFirst();
        }
        if (node == last) {
            return deleteLast();
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
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

    public void deleteList() {
        first = null;
        last = null;
    }

    public int getSize() {
        int listSize = 0;
        DLLNode<E> tmp = first;
        while(tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp.toString();
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            ret += tmp.toString();
            while (tmp.pred != null) {
                tmp = tmp.pred;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }

    public void mirror() {

        DLLNode<E> tmp = null;
        DLLNode<E> current = first;
        last = first;
        while(current!=null) {
            tmp = current.pred;
            current.pred = current.succ;
            current.succ = tmp;
            current = current.pred;
        }

        if(tmp!=null && tmp.pred!=null) {
            first=tmp.pred;
        }
    }

    //PODELI LISTA NA PROSEK I ZAPISI GO REZULTATOT VO POSEBNI LISTI
    public void PodeliListaProsek (DLL<Integer> list) {
        int sum = 0;
        DLLNode<Integer> curr = list.first;

        while (curr != null) {
            sum += curr.element;
            curr = curr.succ;
        }

        float avg = sum / list.getSize();

        DLL<Integer> pomali = new DLL<>();
        DLL<Integer> pogolemi = new DLL<>();
        curr = list.last;

        while (curr != null) {
            if (curr.element <= avg) {
                pomali.insertLast(curr.element);
            } else {
                pogolemi.insertLast(curr.element);
            }
            curr = curr.pred;
        }
        System.out.println(pomali);
        System.out.println(pogolemi);
    }

    //PODELI GI PARNITE I NEPARNITE BROEVI VO POSEBNI LISTA
    public void listTransformation(DLL<Integer> list) {
        DLLNode<Integer> curr = list.first;
        DLL<Integer> parni = new DLL<>();
        DLL<Integer> neParni = new DLL<>();

        while (curr != null) {
            if (curr.element % 2 == 0) {
                parni.insertLast(curr.element);
            } else {
                neParni.insertLast(curr.element);
            }
            curr = curr.succ;
        }

        System.out.println("Neparni: " + neParni);
        System.out.println("Parni: " + parni);
    }

    //SPOJUVANJE NA OPAGJACKA I RASTECKA NIZA VO EDNA KOJA STO KE BIDE OPAGJACKA
    public DLL<Integer> merge (DLL<Integer> list1, DLL<Integer> list2) {
        //list1 rastecka || list2 opagjacka
        DLLNode<Integer> curr1 = list1.last;
        DLLNode<Integer> curr2 = list2.first;
        DLL<Integer> res = new DLL<>();

        while (curr2 != null) {
            res.insertLast(curr2.element);
            curr2 = curr2.succ;
        }

        while (curr1 != null) {
            res.insertLast(curr1.element);
            curr1 = curr1.pred;
        }

        return res;
    }

    //TREBA DA SE NAPRAVAT DVE LISTI, PRVATA TREBA DA GI SODRZI SAMOGLASKITE, VTORATA SOGLASKITE
    public void podeliSamoglaski (DLL<Character> list) {
        DLL<Character> samoglaski = new DLL<>();
        DLL<Character> soglaski = new DLL<>();

        DLLNode<Character> start = list.first;
        DLLNode<Character> end = list.last;

        while (start != null && end != null) {
            if (start.element == 'a'|| start.element == 'e' || start.element == 'o' || start.element == 'u' || start.element == 'i') {
                samoglaski.insertLast(start.element);
            } else {
                soglaski.insertLast(start.element);
            }
            start = start.succ;

            if (start == end) break;

            if (end.element == 'a'|| end.element == 'e' || end.element == 'o' || end.element == 'u' || end.element == 'i') {
                samoglaski.insertLast(end.element);
            } else {
                soglaski.insertLast(end.element);
            }
            end = end.pred;

            if (end == start) break;
        }

        if (start == end && start != null) {
            if (start.element == 'a'|| start.element == 'e' || start.element == 'o' || start.element == 'u' || start.element == 'i') {
                samoglaski.insertLast(start.element);
            } else {
                soglaski.insertLast(start.element);
            }
        }

        System.out.println("Samoglaski = [ " + samoglaski + " ]");
        System.out.println("Soglaski = [ " + soglaski + " ]");
    }
}
