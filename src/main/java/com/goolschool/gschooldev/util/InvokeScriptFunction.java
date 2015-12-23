/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goolschool.gschooldev.util;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class InvokeScriptFunction {
    
    public static void main(String runscript,String invokeFun,String param) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
 
        // JavaScript code in a String
        String script = runscript;//"function hello(name) { print('Hello, ' + name); }";
        // evaluate script
        engine.eval(script);
 
        Invocable inv = (Invocable) engine;
 
        // invoke the global function named "hello"
        inv.invokeFunction(invokeFun, param );
    }
    
    public static void runScript(String runscript) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
 
        // JavaScript code in a String
        String script = runscript;//"function hello(name) { print('Hello, ' + name); }";
        // evaluate script
        engine.eval(script);
 
        Invocable inv = (Invocable) engine;
    }
}