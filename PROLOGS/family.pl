% Facts
male(narayan).
male(prabhakar).
male(krushna).
male(dhananjay).
male(jay).
male(yashraj).
male(mohit).
male(makarand).
male(harsh).
male(ishwar).
male(vijay).

female(shantai).
female(sangita).
female(shindu).
female(shillpa).
female(surekha).
female(radha).
female(vrushali).
female(sakshi).
female(pooja).
female(divya).
female(rashi).
female(neha).
female(ashita).

% Parent relations
parent(narayan,prabhakar).
parent(narayan,krushna).
parent(narayan,dhananjay).
parent(narayan,surekha).
parent(narayan,radha).
parent(prabhakar,mohit).
parent(prabhakar,vrushali).
parent(krushna,makarand).
parent(krushna,divya).
parent(dhananjay,sakshi).
parent(dhananjay,pooja).
parent(jay,ishwar).
parent(jay,rashi).
parent(yashraj,harsh).
parent(yashraj,neha).
parent(vijay,ashita).
parent(shantai,prabhakar).
parent(shantai,krushna).
parent(shantai,dhananjay).
parent(shantai,surekha).
parent(shantai,radha).
parent(sangita,mohit).
parent(sangita,vrushali).
parent(shindu,makarand).
parent(shindu,divya).
parent(shillpa,sakshi).
parent(shillpa,pooja).
parent(surekha,ishwar).
parent(surekha,rashi).
parent(radha,harsh).
parent(radha,neha).
parent(divya,ashita).

mother(X,Y) :- parent(X,Y), female(X).
father(X,Y) :- parent(X,Y), male(X).

wife(X,Y) :- female(X), male(Y), parent(X,Z), parent(Y,Z), X \= Y.
husband(X,Y) :- male(X), female(Y), parent(X,Z), parent(Y,Z), X \= Y.

sibling(X,Y) :- parent(P,X), parent(P,Y),X \= Y.

greatgrandfather(X,Y) :- male(X), grandparent(_, Y).

grandparent(X,Z) :- parent(X,Y), parent(Y,Z).
grandmother(X,Z) :- parent(X,Y), parent(Y,Z), female(X).
grandfather(X,Z) :- parent(X,Y), parent(Y,Z), male(X).

uncle(X,Y) :- parent(P,Y), sibling(X,P), male(X).
aunt(X,Y) :- parent(P,Y), sister(X,P).

cousin(X,Y) :- parent(P1,X), parent(P2,Y), sibling(P1,P2), X \= Y.
sister(X,Y) :- female(X), sibling(X,Y).
brother(X,Y) :- male(X), sibling(X,Y).

fatherinlaw(X,Y) :- wife(Z,Y), father(X,Z),male(Y).
motherinlaw(X,Y) :- wife(Z,Y), mother(X,Z),male(Y).
brotherinlaw(X,Y) :- wife(Z,Y), brother(X,Z),male(Y).
soninlaw(X,Y) :- husband(Y,Z), father(X,Z) ,male(Y).

niece(X,Y) :- female(X), mother(Z,X), sister(Z,Y).
nephew(X,Y) :- male(X), mother(Z,X), sister(Z,Y).