/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task4;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author m_nou
 */
public class Task4 {

    public static Stack<Integer> numsStack = new Stack<>();
    public static Stack<Character> op = new Stack<>();
    public static Queue<Integer> numsQueue = new LinkedList<Integer>();
    public static Queue<Character> opQueue = new LinkedList<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int in = -1;
        do {
          
            String inputt = JOptionPane.showInputDialog(null, "Press 1 to Calculate an Expression using stack" + "\n"
                    + "Press 2 to Calculate an Expression using queue" + "\n"
                    + "Press 0 to Exit", "Linear Algebric Calculator", -1);
            in = Integer.parseInt(inputt);
            switch (in) {
                case 1:
                    String input = JOptionPane.showInputDialog(null, "Enter The Expression", "Algebric Expression Calculator", -1);
                    solve_expression(input);
                    break;
                case 2:
                    String queue_in = JOptionPane.showInputDialog(null, "Enter The Expression", "Algebric Expression Calculator", -1);
                    solve_queu(queue_in);
                    break;
                case 0:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid Input");
            }
        } while (in != 0);

    }

    static boolean balance_eq(String s) {
        Stack<Character> opp = new Stack<>();

        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '[' || arr[i] == '{' || arr[i] == '(') {
                opp.push(arr[i]);
            } else if (arr[i] == ')') {
                char c = opp.peek();
                if (c == '(') {
                    opp.pop();
                } else {
                    return false;
                }
            } else if (arr[i] == '}') {
                char c = opp.peek();
                if (c == '{') {
                    opp.pop();
                } else {
                    return false;
                }
            } else if (arr[i] == ']') {
                char c = opp.peek();
                if (c == '[') {
                    opp.pop();
                } else {
                    return false;
                }
            }
        }
        if (opp.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    static void solve_queu(String input)
    {
          numsQueue = new LinkedList<>();
          opQueue = new LinkedList<>();
                    if (balance_eq(input)) {
                        char[] arr = input.toCharArray();
                        int i = 0;
                        while (i < arr.length) {
                            if (arr[i] >= '0' && arr[i] <= '9') {
                                // System.out.println(i);
                                int k = 0;
                                String inte = "";
                                for (int j = i; j < arr.length; j++) {
                                    if (arr[j] >= '0' && arr[j] <= '9') {
                                        inte = inte + arr[j];
                                        //   System.out.println(numsStack.size() + "in if");
                                        k++;

                                    } else {
                                        numsQueue.add(Integer.parseInt(inte));
                                        // System.out.println(numsStack.size() + "in else");
                                        break;
                                    }
                                    if (j == arr.length - 1) {
                                        numsQueue.add(Integer.parseInt(inte));
                                        //System.out.println(numsStack.size() + "in else-if");
                                        break;
                                    }
//                 
                                }
                                i = i + k;

                              //  System.out.println(numsStack.peek());

                            } else if (arr[i] == '+') {
//    
                                opQueue.add(arr[i]);
                                i++;
                            } else if (arr[i] == '-') {
//     
                                opQueue.add(arr[i]);
                                i++;
                            } else if (arr[i] == 'x' || arr[i] == 'X' || arr[i] == '*') {
                                //op.push(arr[i]);
                                if (arr[i + 1] >= '0' && arr[i + 1] <= '9') {
                                    
                                    int num = num_iitart();
                                    
                                    numsQueue.remove(num);
                                    numsQueue.add(num * Character.getNumericValue(arr[i + 1]));
                                    i = i + 2;
                                } else if (arr[i + 1] == '[' || arr[i + 1] == '{' || arr[i + 1] == '(') {
                                    opQueue.add(arr[i]);
                                    i++;
                                }
                                
                            } else if (arr[i] == '/') {
                                //op.push(arr[i]);
                                if (arr[i + 1] >= '0' && arr[i + 1] <= '9') {
                                    Iterator<Integer> itr = numsQueue.iterator();
                                    int num = -1;
                                    for(int k=0;k<numsQueue.size();k++)
                                    {
                                        num = itr.next();
                                    }
                                    numsQueue.remove(num);
                                    numsQueue.add(num * Character.getNumericValue(arr[i + 1]));
                                    i = i + 2;
                                } else if (arr[i + 1] == '[' || arr[i + 1] == '{' || arr[i + 1] == '(') {
                                    opQueue.add(arr[i]);
                                    i++;
                                }
                            } else if (arr[i] == '[' || arr[i] == '{' || arr[i] == '(') {
                                opQueue.add(arr[i]);
                                System.out.println(char_iitart());
                                i++;
                            } else if (arr[i] == ')') {
                                while (true) {
                                    char num = char_iitart();
                                    int a = -1 ;
                                    int b = -1 ;
                                    if (num == '(') {
                                        opQueue.remove(num);
                                            num = char_iitart();
                                        if (num == '+') {
                                            a = num_iitart();
                                            numsQueue.remove(a);
                                            b = num_iitart();
                                            numsQueue.remove(b);
                                            numsQueue.add( a + b );
                                            opQueue.remove(num);
                                        } else if (num == '-') {
                                             a = num_iitart();
                                            numsQueue.remove(a);
                                            b = num_iitart();
                                            numsQueue.remove(b);
                                            numsQueue.add( a - b );
                                            opQueue.remove(num);
                                           
                                        } else if (num == 'x' || num == 'X' || num == '*') {
                                             a = num_iitart();
                                            numsQueue.remove(a);
                                            b = num_iitart();
                                            numsQueue.remove(b);
                                            numsQueue.add( a * b );
                                            opQueue.remove(num);
                                        } else if (num == '/') {
                                            a = num_iitart();
                                            numsQueue.remove(a);
                                            b = num_iitart();
                                            numsQueue.remove(b);
                                            numsQueue.add( a / b );
                                            opQueue.remove(num);
                                        }
                                        break;
                                    } else if (num == '+') {
                                             a = num_iitart();
                                            numsQueue.remove(a);
                                            b = num_iitart();
                                            numsQueue.remove(b);
                                            numsQueue.add( a + b );
                                            opQueue.remove(num);
                                    } else if (num == '-') {
                                            a = num_iitart();
                                            numsQueue.remove(a);
                                            b = num_iitart();
                                            numsQueue.remove(b);
                                            numsQueue.add( a - b );
                                            opQueue.remove(num);
                                    }
                                }
                                i++;
                            } else if (arr[i] == '}') {
                                while (true) {
                                    char num = char_iitart();
                                    int a = -1;
                                    int b = -1;
                                    if ( num == '{' ) {
                                         opQueue.remove(num);
                                            num = char_iitart();
                                        if (num == '+') {
                                            a = num_iitart();
                                            numsQueue.remove(a);
                                            b = num_iitart();
                                            numsQueue.remove(b);
                                            numsQueue.add( a + b );
                                            opQueue.remove(num);
                                        } else if (num == '-') {
                                             a = num_iitart();
                                            numsQueue.remove(a);
                                            b = num_iitart();
                                            numsQueue.remove(b);
                                            numsQueue.add( a - b );
                                            opQueue.remove(num);
                                           
                                        } else if (num == 'x' || num == 'X' || num == '*') {
                                             a = num_iitart();
                                            numsQueue.remove(a);
                                            b = num_iitart();
                                            numsQueue.remove(b);
                                            numsQueue.add( a * b );
                                            opQueue.remove(num);
                                        } else if (num == '/') {
                                            a = num_iitart();
                                            numsQueue.remove(a);
                                            b = num_iitart();
                                            numsQueue.remove(b);
                                            numsQueue.add( a / b );
                                            opQueue.remove(num);
                                        }
                                        break;
                                    } else if (num == '+') {
                                            a = num_iitart();
                                            numsQueue.remove(a);
                                            b = num_iitart();
                                            numsQueue.remove(b);
                                            numsQueue.add( a + b );
                                            opQueue.remove(num);
                                    } else if (num == '-') {
                                            a = num_iitart();
                                            numsQueue.remove(a);
                                            b = num_iitart();
                                            numsQueue.remove(b);
                                            numsQueue.add( a - b );
                                            opQueue.remove(num);
                                    }
                                }
                                i++;
                            } else if (arr[i] == ']') {
                                while (true) {
                                     char num = char_iitart();
                                    int a = -1;
                                    int b = -1;
                                    if (num == '[') {
                                       opQueue.remove(num);
                                            num = char_iitart();
                                        if (num == '+') {
                                            a = num_iitart();
                                            numsQueue.remove(a);
                                            b = num_iitart();
                                            numsQueue.remove(b);
                                            numsQueue.add( a + b );
                                            opQueue.remove(num);
                                        } else if (num == '-') {
                                             a = num_iitart();
                                            numsQueue.remove(a);
                                            b = num_iitart();
                                            numsQueue.remove(b);
                                            numsQueue.add( a - b );
                                            opQueue.remove(num);
                                           
                                        } else if (num == 'x' || num == 'X' || num == '*') {
                                             a = num_iitart();
                                            numsQueue.remove(a);
                                            b = num_iitart();
                                            numsQueue.remove(b);
                                            numsQueue.add( a * b );
                                            opQueue.remove(num);
                                        } else if (num == '/') {
                                            a = num_iitart();
                                            numsQueue.remove(a);
                                            b = num_iitart();
                                            numsQueue.remove(b);
                                            numsQueue.add( a / b );
                                            opQueue.remove(num);
                                        }
                                        break;
                                    } else if (num == '+') {
                                            a = num_iitart();
                                            numsQueue.remove(a);
                                            b = num_iitart();
                                            numsQueue.remove(b);
                                            numsQueue.add( a + b );
                                            opQueue.remove(num);
                                    } else if (num == '-') {
                                            a = num_iitart();
                                            numsQueue.remove(a);
                                            b = num_iitart();
                                            numsQueue.remove(b);
                                            numsQueue.add( a - b );
                                            opQueue.remove(num);
                                    }
                                }
                                i++;
                            }

                        }
                        while (!opQueue.isEmpty()) {
                            char q = char_iitart();
                            if (q == '+') {
                                //  System.out.println(numsStack.size());
                                       int a = -1;
                                    int b = -1;     
                                a = num_iitart();
                                            numsQueue.remove(a);
                                            b = num_iitart();
                                            numsQueue.remove(b);
                                            numsQueue.add( a + b );
                                            opQueue.remove(q);
                                
                            }
                            if (q == '-') {
                                    int a = -1;
                                    int b = -1;     
                                             a = num_iitart();
                                            numsQueue.remove(a);
                                            b = num_iitart();
                                            numsQueue.remove(b);
                                            numsQueue.add( a + b );
                                            opQueue.remove(q);
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Answer is " + numsQueue.poll());
                    }
                    else{
                    JOptionPane.showMessageDialog(null,"Entered Expression is Not Balanced","Error",0);
                    }
                    }
    static int num_iitart()
    {
        Iterator<Integer> itr = numsQueue.iterator();
        int num = -1 ;
        for(int k=0;k<numsQueue.size();k++)
                                     {
                                        num = itr.next();
                                     }
        return num;
    }
    
    static char char_iitart()
    {
        Iterator<Character> itr = opQueue.iterator();
        char num = 'a' ;
        for(int k=0;k<opQueue.size();k++)
                                     {
                                        num = itr.next();
                                     }
        return num;
    }
    static void solve_expression(String input)
    {
                     numsStack = new Stack<>();
                     op = new Stack<>();
                    if (balance_eq(input)) {
                        char[] arr = input.toCharArray();
                        int i = 0;
                        while (i < arr.length) {
                            if (arr[i] >= '0' && arr[i] <= '9') {
                                // System.out.println(i);
                                int k = 0;
                                String inte = "";
                                for (int j = i; j < arr.length; j++) {
                                    if (arr[j] >= '0' && arr[j] <= '9') {
                                        inte = inte + arr[j];
                                        //   System.out.println(numsStack.size() + "in if");
                                        k++;

                                    } else {
                                        numsStack.push(Integer.parseInt(inte));
                                        // System.out.println(numsStack.size() + "in else");
                                        break;
                                    }
                                    if (j == arr.length - 1) {
                                        numsStack.push(Integer.parseInt(inte));
                                        //System.out.println(numsStack.size() + "in else-if");
                                        break;
                                    }
//                 
                                }
                                i = i + k;

                              //  System.out.println(numsStack.peek());

                            } else if (arr[i] == '+') {
//    
                                op.push(arr[i]);
                                i++;
                            } else if (arr[i] == '-') {
//     
                                op.push(arr[i]);
                                i++;
                            } else if (arr[i] == 'x' || arr[i] == 'X' || arr[i] == '*') {
                                //op.push(arr[i]);
                                if (arr[i + 1] >= '0' && arr[i + 1] <= '9') {
                                    numsStack.push(numsStack.pop() * Character.getNumericValue(arr[i + 1]));
                                    i = i + 2;
                                } else if (arr[i + 1] == '[' || arr[i + 1] == '{' || arr[i + 1] == '(') {
                                    op.push(arr[i]);
                                    i++;
                                }
                            } else if (arr[i] == '/') {
                                //op.push(arr[i]);
                                if (arr[i + 1] >= '0' && arr[i + 1] <= '9') {
                                    numsStack.push(numsStack.pop() / Character.getNumericValue(arr[i + 1]));
                                    i = i + 2;
                                } else if (arr[i + 1] == '[' || arr[i + 1] == '{' || arr[i + 1] == '(') {
                                    op.push(arr[i]);
                                    i++;
                                }
                            } else if (arr[i] == '[' || arr[i] == '{' || arr[i] == '(') {
//                                System.out.println(op.peek());
                                op.push(arr[i]);
                                i++;
                            } else if (arr[i] == ')') {
                                while (true) {
                                    char c = op.peek();
                                    if (c == '(') {
                                        op.pop();
                                        char w = op.peek();
                                        if (w == '+') {
                                            numsStack.push(numsStack.pop() + numsStack.pop());
                                            op.pop();
                                        } else if (w == '-') {
                                            numsStack.push(numsStack.pop() - numsStack.pop());
                                            op.pop();
                                        } else if (w == 'x' || w == 'X' || w == '*') {
                                            numsStack.push(numsStack.pop() * numsStack.pop());
                                            op.pop();
                                        } else if (w == '/') {
                                            numsStack.push(numsStack.pop() / numsStack.pop());
                                            op.pop();
                                        }
                                        break;
                                    } else if (c == '+') {
                                        numsStack.push(numsStack.pop() + numsStack.pop());
                                        op.pop();
                                    } else if (c == '-') {
                                        numsStack.push(numsStack.pop() - numsStack.pop());
                                        op.pop();
                                    }
                                }
                                i++;
                            } else if (arr[i] == '}') {
                                while (true) {
                                    char c = op.peek();
                                    if (c == '{') {
                                        op.pop();
                                        char w = op.peek();
                                        if (w == '+') {
                                            numsStack.push(numsStack.pop() + numsStack.pop());
                                            op.pop();
                                        } else if (w == '-') {
                                            numsStack.push(numsStack.pop() - numsStack.pop());
                                            op.pop();
                                        } else if (w == 'x' || w == 'X' || w == '*') {
                                            numsStack.push(numsStack.pop() * numsStack.pop());
                                            op.pop();
                                        } else if (w == '/') {
                                            numsStack.push(numsStack.pop() / numsStack.pop());
                                            op.pop();
                                        }
                                        break;
                                    } else if (c == '+') {
                                        numsStack.push(numsStack.pop() + numsStack.pop());
                                        op.pop();
                                    } else if (c == '-') {
                                        numsStack.push(numsStack.pop() - numsStack.pop());
                                        op.pop();
                                    }
                                }
                                i++;
                            } else if (arr[i] == ']') {
                                while (true) {
                                    char c = op.peek();
                                    if (c == '[') {
                                        op.pop();
                                        char w = op.peek();
                                        if (w == '+') {
                                            numsStack.push(numsStack.pop() + numsStack.pop());
                                            op.pop();
                                        } else if (w == '-') {
                                            numsStack.push(numsStack.pop() - numsStack.pop());
                                            op.pop();
                                        } else if (w == 'x' || w == 'X' || w == '*') {
                                            numsStack.push(numsStack.pop() * numsStack.pop());
                                            op.pop();
                                        } else if (w == '/') {
                                            numsStack.push(numsStack.pop() / numsStack.pop());
                                            op.pop();
                                        }
                                        break;
                                    } else if (c == '+') {
                                        numsStack.push(numsStack.pop() + numsStack.pop());
                                        op.pop();
                                    } else if (c == '-') {
                                        numsStack.push(numsStack.pop() - numsStack.pop());
                                        op.pop();
                                    }
                                }
                                i++;
                            }

                        }
                        while (!op.empty()) {
                            char q = op.peek();
                            if (q == '+') {
                                //  System.out.println(numsStack.size());

                                numsStack.push(numsStack.pop() + numsStack.pop());
                                op.pop();
                            }
                            if (q == '-') {
                                numsStack.push(numsStack.pop() - numsStack.pop());
                                op.pop();
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Answer is " + numsStack.pop());
                    }
                    else{
                    JOptionPane.showMessageDialog(null,"Entered Expression is Not Balanced","Error",0);
                    }
                    
    }

}
