/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swinglogin;

/**
 *
 * @author aghos
 */
public interface x {
        public void method1() ;
        
 	public default void method2() {
 		System.out.println("method2");
 	}
 	public static void method3() {
 		System.out.println("method3");
 	}
 	//public abstract void method4();
}
