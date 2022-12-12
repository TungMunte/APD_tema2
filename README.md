Nivel 1 de paralizarea : ( HandleOrders class)
- fiecare thread va citi comenzi de la fisierul orders.txt si va stoca linii appropriate cu ID-ul lui
- fiecare thread va chema thread-ul de la nivel 2 de paralizarea si va primi catre fiecare thread de nivelul 2 fiecare comand stocat.

Nivel 2 de paralizarea : ( HandleOrderProduct class) 
- thread va parsa command catre id_command si numarul
- fiecare thread va citi date de la fisierul order_products.txt si scrie catre fisierul order_products_out.txt
- atunci compara contintul din linii citii si id_command
- dupa toate comenzi se livreaza , se scrie catre fisierul orders_out.txt