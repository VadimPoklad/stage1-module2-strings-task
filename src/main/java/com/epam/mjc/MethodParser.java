package com.epam.mjc;

import java.util.*;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {


        String stringArguments = signatureString.substring(signatureString.indexOf("(")+1,signatureString.indexOf(")"));
        String other = signatureString.substring(0,signatureString.indexOf("("));

        List<MethodSignature.Argument> arguments = getArguments(stringArguments);
        ArrayList<String> arrayList = getInfo(other);

        MethodSignature methodSignature =new MethodSignature(arrayList.get(arrayList.size()-1),arguments);

        if(arrayList.size()==3){
            methodSignature.setReturnType(arrayList.get(1));
            methodSignature.setAccessModifier(arrayList.get(0));
        }else {
            methodSignature.setReturnType(arrayList.get(0));
        }

        return methodSignature;
    }

    private List<MethodSignature.Argument> getArguments(String arg){
        List<MethodSignature.Argument> list = new LinkedList<>();
        StringTokenizer arguments = new StringTokenizer(arg,", ",false);

            while(arguments.hasMoreElements()){
                MethodSignature.Argument argument = new MethodSignature.Argument(arguments.nextToken(),arguments.nextToken());
                list.add(argument);
            }

        return list;
    }

    private ArrayList<String> getInfo(String line){
        ArrayList<String> list = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(line," ");
        while (stringTokenizer.hasMoreTokens()){
            list.add(stringTokenizer.nextToken());
        }
        return list;
    }
}
