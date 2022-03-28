package com.company;




import java.util.Random;




public class Main {




    public static int heroesHealth[] = {270, 280, 260, 530, 150}; //воин, маг и кинетик

    public static int[] heroesDamage = {20, 15, 25, 16, 21};

    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Alchemist", "Luck"};




    public static int bossHealth = 700;

    public static  int bossDamage = 50;

    public static String bossDefenceType;

    public static int roundNumber = 0;




    public static String[] newHeroes  = {heroesAttackType [3] , heroesAttackType[4]};

    public static int[] newHeroesHealth = {heroesHealth[3] , heroesHealth[4]};




    public static  int bossDamageSaveGolem = (bossDamage / 5);




    public static boolean luckyDodge() {

        Random luck = new Random();

        boolean randomLucky = luck.nextBoolean();

        return randomLucky;

    }




    public static void chooseBossDefenceType() {

        Random random = new Random();

        int randNum = random.nextInt(heroesAttackType.length);

        bossDefenceType = heroesAttackType[randNum];

        System.out.println("Boss choose: " + bossDefenceType);

    }




    public static void heroesHit() {

        for (int i = 0; i < heroesDamage.length; i++) {

            if(heroesHealth[i] > 0 && bossHealth > 0){

                if(heroesAttackType[i] == bossDefenceType) {

                    Random r = new Random();

                    int coefficient = r.nextInt(8) + 2;




                    if(bossHealth < heroesDamage[i] * coefficient){

                        bossHealth = 0;

                    } else {

                        bossHealth =  bossHealth - heroesDamage[i] * coefficient;

                    }

                    System.out.println("Critical Damage: " + heroesDamage[i] * coefficient);

                } else {

                    if(bossHealth < heroesDamage[i]){

                        bossHealth = 0;

                    } else {

                        bossHealth =  bossHealth - heroesDamage[i];

                    }

                }

            }

        }

    }

    public static void bossHits(){

        for (int i = 0; i < heroesHealth.length; i++) {

            if (newHeroesHealth[0] > ((bossDamage - (bossDamageSaveGolem)) * 4)) {

                if (luckyDodge() == true) {

                    heroesHealth[4] = heroesHealth[4] - bossDamage + bossDamage;

                    heroesHealth[i] = heroesHealth[i] - (bossDamage - (bossDamageSaveGolem));

                    newHeroesHealth[0] = newHeroesHealth[0] - (bossDamageSaveGolem * 4);

                    if (heroesHealth[i] < bossDamage) {

                        heroesHealth[i] = 0;

                    }

                }

            }else{

                heroesHealth[i] =  heroesHealth[i] - bossDamage;

            }




        }




    }

    public static void main(String[] args) {

        printDStatistics();

        isGameFinished();

        while (!isGameFinished()) {

            round();

        }

        System.out.println(luckyDodge());




    }

    public static Boolean isGameFinished() {

        if(bossHealth <= 0){

            System.out.println("Heroes won!");

            return true;

        }




        boolean allHeroesDead = true;

        for (int i = 0; i < heroesHealth.length; i++) {

            if(heroesHealth[i] > 0){

                allHeroesDead = false;

                break;

            }

        }




        if (allHeroesDead){

            System.out.println("BOSS WON!");

        }

        return allHeroesDead;

    }




    private static void round() {

        roundNumber++;

        chooseBossDefenceType();

        luckyDodge();

        bossHits();

        heroesHit();

        printDStatistics();




    }







    public static void printDStatistics() {

        System.out.println(roundNumber + " ROUND");

        System.out.println("Boss health: " + bossHealth + " [" + bossDamage + "]" );

        for (int i = 0; i < heroesHealth.length ; i++) {

            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " ["  + heroesDamage[i] + "]" );

        }

    }







}