;Move the first three bits of a Word B with the last 3 bits of the Byte A

data_ SEGMENT ; declaring the data segment
	a db 11100000b
	b dw 0000000000000000b
data_ ENDS

text_ SEGMENT
start:
	mov ax, data_ ; clearing the memory 
	mov ds,ax ;loading the data segment

	mov ax,b ; ax=b    
	mov bl,a ; bl = 11100000b
	mov bh,0 ; bx = 0000 0000 1110 0000b
	shr bx,5 ; bx = 0000 0000 0000 0111b
	or ax,bx ; ax = 0000 0000 0000 0111b
	
	
	mov ax, 4c00h    ;terminating the program
    int 21h
text_ ENDS

END start
