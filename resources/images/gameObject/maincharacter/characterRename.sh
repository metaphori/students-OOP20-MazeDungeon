#!/bin/bash
i = 1
while ((i < 9)); do 
	mv ./character${i}.png ./player${i}.png
	(( i=$i+1 ))
done;
		
