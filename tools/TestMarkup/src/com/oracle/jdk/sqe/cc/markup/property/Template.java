/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package com.oracle.jdk.sqe.cc.markup.property;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.DocErrorReporter;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.RootDoc;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author shura
 */
public class Template {

    public static boolean start(RootDoc doc) throws FileNotFoundException, IOException {
        if(out == null) out = System.out;
        for (ClassDoc cls : doc.classes()) {
            Map<String, MethodDoc> allProps = getPropNames(cls);
            for (MethodDoc m : cls.methods()) {
                String propName = getPropName(m, true);
                if (propName != null) {
                    MethodDoc mDeclaring = allProps.get(propName);
                    if (mDeclaring != null) {
                        boolean supported = false;
                        if (m.name().startsWith("get") || m.name().startsWith("is")) {
                            supported = m.returnType().equals(mDeclaring.returnType());
                        } else if (m.name().startsWith("set")) {
                            supported = m.parameters()[0].type().equals(mDeclaring.returnType());
                        }
                        if (supported) {
                            out.println(cls.qualifiedName() + "." + propName);
                        }
                    }
                }
            }
        }
        return true;
    }

    private static Map<String, MethodDoc> getPropNames(ClassDoc cls) {
        HashMap<String, MethodDoc> res = new HashMap<String, MethodDoc>();
        do {
            for (MethodDoc m : cls.methods()) {
                String name = getPropName(m, false);
                if (name != null) {
                    res.put(name, m);
                }
            }
        } while ((cls = cls.superclass()) != null);
        return res;
    }

    protected static String getPropName(MethodDoc m, boolean includeSetters) {
        if (m.parameters().length > 0) {
            return null;
        }
        if (m.name().startsWith("get") && m.name().length() > 3) {
            if (m.returnType().typeName().equals(Void.TYPE.getName())) {
                return null;
            }
            return m.name().substring(3, 4).toLowerCase() + m.name().substring(4);
        }
        if (m.name().startsWith("is") && m.name().length() > 2) {
            if (m.returnType().typeName().equals(Boolean.TYPE.getName())) {
                return null;
            }
            return m.name().substring(2, 3).toLowerCase() + m.name().substring(3);
        }
        if (includeSetters && m.name().startsWith("set") && m.name().length() > 3) {
            if (!m.returnType().typeName().equals(Void.TYPE.getName())
                    || m.parameters().length != 1) {
                return null;
            }
            return m.name().substring(3, 4).toLowerCase() + m.name().substring(4);
        }
        return null;
    }

    public static int optionLength(String option) {
        if (option.equals("-template")) {
            return 2;
        }
        return 0;
    }
    
    private static PrintStream out = null;

    public static boolean validOptions(String options[][],
            DocErrorReporter reporter) throws IOException {
        for (int i = 0; i < options.length; i++) {
            String[] opt = options[i];
            if (opt[0].equals("-template")) {
                out = new PrintStream(opt[1]);
            }
        }
        return true;
    }
}
