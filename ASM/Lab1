The code is ment to calculate the next expression (a-b*c/d)/(c+2-a/b)+5
ASSUME cs:text_, ds:data_

//
//  The code bellow it for unsigned numbers
//

data_ SEGMENT
    a db 10         
    b db 2
    c dw 5          
    d db 1          
    rez dw ?        
data_ ENDS

; Define the code segment
text_ SEGMENT
start:
    ; Initialize the data segment register (DS) to point to the data segment
    mov ax, data_  
    mov ds, ax      

    ; Calculate b * c / d
    mov al, b       ; Move the value of 'b' 
    mov ah, 0       ; Clear AH 
    mov cx, c       ; Move the value of 'c' 
    mul cx          ; Multiply AX by CX , result in AX 
    mov bl, d       ; Move the value of 'd'  into BL
    div bl          ; Divide AX by BL , result in AL , remainder in AH 
    mov bx, ax      ; Move the result into BX for later use

    ; Calculate a - (b * c / d)
    mov al, a       ; Move the value of 'a' into AL
    sub ax, bx      ; Subtract BX from AX, result in AX 

    ; Calculate a / b
    mov al, a       ; Move the value of 'a'  into AL
    mov bl, b       ; Move the value of 'b'  into BL
    xor ah, ah      ; Clear  AH
    div bl          ; Divide AX by BL , result in AL , remainder in AH 

    ; Calculate c + 2 - (a / b)
    mov cx, c       ; Move the value of 'c'  into CX
    add cx, 2       ; Add 2 to CX 
    sub cx, ax      ; Subtract AX  from CX , result in CX 

    ; Calculate (a - b * c / d) / (c + 2 - a / b)
    mov bx, cx      ; Move the result of (c + 2 - a / b) into BX
    div bx          ; Divide AX by BX , result in AX , remainder in DX 

    ; Add 5 to the result
    add ax, 5       ; Add 5 to AX 
    mov rez, ax     ; Move the final result into 'rez'

    ; Program termination
    mov ax, 4c00h   ; Set AH to 4Ch (DOS function for program termination) and AL to 00h
    int 21h         ; Call interrupt 21h to terminate the program

text_ ENDS

END start

END start

//
//    The code bellow is for signed numbers 
//

	ASSUME cs:text_, ds:data_

	data_ SEGMENT
		a db 1
		b db 1         
		c dw -2      
		d db 1          
		rez dw ?         
	data_ ENDS

	text_ SEGMENT
	start:
		mov ax, data_    
		mov ds, ax
		;b * c / d
		mov al, b       
		cbw              
		mov cx, c        
		imul cx          
		mov bl, d        
		cwd              
		idiv bl          
		mov bx, ax       
		
		;a - (b * c / d)
		mov al, a       
		cbw             
		sub ax, bx       
		
		;a / b
		mov al, a        
		cbw              
		mov bl, b        
		cwd              
		idiv bl         

		;c + 2 - (a / b)
		mov cx, c       
		add cx, 2        
		sub cx, ax       
		mov bx, cx      
		
		;(a - b * c / d) / (c + 2 - a / b)

		cwd           
		idiv bx         
		
		mov rez, ax      

	   
		mov ax, 4c00h    
		int 21h
	text_ ENDS

	END start

