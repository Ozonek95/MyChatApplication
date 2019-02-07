package com.mychat.com.chat.domain;

import java.util.Random;

public class DrunkChannel implements Channel {

    private StringBuilder builder = new StringBuilder();
    private Random random = new Random();

    @Override
    public String writeToStringBuilder(String message) {
        String[] nicknameCatcher = message.split(":");
        String nickname = nicknameCatcher[0];
        String afterNickname = separateNickname(message);
        return nickname+changeRandomLettersInWords(splitString(afterNickname));
    }

    private String[] splitString(String message) {
        return message.split(" ");
    }

    private String changeRandomLettersInWords(String[] words) {
        builder.delete(0,builder.toString().length());
        for (String word : words) {
            char[] letters = word.toCharArray();
            if (letters.length > 2) {
                int generateRandomIntToChangeWordInSixtySixPercent = random.nextInt((3)+1);
                if ((generateRandomIntToChangeWordInSixtySixPercent % 2 == 0 || generateRandomIntToChangeWordInSixtySixPercent %3 ==0)) {

                    for (int i = 1; i < letters.length - 1; i++) {
                        //change random letter 25% chance
                        if ((random.nextInt(4) + 1) % 4 == 0) {
                            letters[i] = (char) (random.nextInt((122 - 97) + 1) + 97);
                        }
                    }
                }
            }
            builder.append(letters).append(" ");
        }

        return builder.toString();
    }

    private String separateNickname(String message){
       for (int i=0;i<message.length();i++){
           if(message.charAt(i)==':'){
               return message.substring(i);
           }
       }
       return message;
    }

}
