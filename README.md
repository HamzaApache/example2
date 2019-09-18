# Notre projet
La classe ATM reproduit le d�roulement simplifi� d'une op�ration de retrait de liquide � un distributeur:
1. Le client s�lectionne le montant du retrait
2. On v�rifie que le montant est disponible dans la machine
3. On r�alise le paiement du montant avec la carte de l'utilisateur
4. On d�livre les billets

La classe ATM s'appuie sur trois composants pour mener sa t�che � bien:
* AmountSelector: la m�thode selectAmount() correspond � l'�tape 1
* CashManager: les m�thodes canDeliver(int) pour l'�tape 2 et deliver(int) pour l'�tape 4
* PaymentProcessor: la m�thode pay(int) pour l'�tape 3

Le code inclut une interface pour chacun de ces trois composants mais pas d'impl�mentation.

# Objectif de l'exercice
Alimenter la classe de test ATMTest avec tous les cas de test unitaire de la classe ATM que vous jugerez utiles. Attention: un bug s'est gliss� dans l'impl�mentation d'ATM. Saurez-vous le d�busquer ?