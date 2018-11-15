FILES=Consommateur.java Producteur.java Tampon.java main.java Client.java Serveur.java

main : $(FILES)
	javac $(FILES)

clear :
	rm *.class

exec :
	java main
