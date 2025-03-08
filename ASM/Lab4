;From a given row of characters with lower and uppercase letters. It will show on the screen the lower case letters and the number of lower case lettersASSUME cs:text_, ds:data_
; Assume the code segment (cs) and data segment (ds) are correctly set
ASSUME cs:text_, ds:data_

; Define the data segment
data_ SEGMENT
    sir db 'AaBbCcDdEeFf','$'  
    lmici dw 0                 
data_ ENDS

; Define the code segment
text_ SEGMENT
start:
    ; Initialize the data segment register (DS) to point to the data segment
    mov ax, data_  
    mov ds, ax     

    ; Initialize SI to point to the beginning of the string 'sir'
    mov si, offset sir  ; Load the offset address of 'sir' into SI
    xor cx, cx          ; Clear CX 

repeta:
    ; Load the current character from the string into AL
    mov al, [si]  ; Move the byte at the address pointed to by SI into AL
    inc si        ; Increment SI to point to the next character in the string

    ; Check if the current character is the end of the string ('$')
    cmp al, '$'   ; Compare AL with '$' 
    je sfarsit    ; If AL == '$', jump to 'sfarsit' 

    ; Check if the character is a lowercase letter 
    cmp al, 'a'   ; Compare AL with 'a'
    jl urm_char   ; If AL < 'a', jump to 'urm_char' 
    cmp al, 'z'   ; Compare AL with 'z'
    jg urm_char   ; If AL > 'z', jump to 'urm_char' (

    ; If the character is a lowercase letter, print it
    mov dl, al    ; Move the lowercase letter from AL to DL 
    mov ah, 02h   ; Set AH to 02h 
    int 21h       ; Call interrupt 21h to print the character in DL

    ; Increment the counter for lowercase letters
    inc cx        ; Increment CX 

urm_char:
    ; Jump back to the start of the loop to process the next character
    jmp repeta    ; Jump to 'repeta' to process the next character

sfarsit:
    ; Move the count of lowercase letters (CX) into AX
    mov ax, cx    ; Move the value of CX into AX

    ; Call the procedure to print the number in AX
    call print_numar  ; Call the 'print_numar' procedure to print the number in AX

    ; Program termination
    mov ah, 4Ch   ; Set AH to 4Ch 
    int 21h       ; Call interrupt 21h to terminate the program

; Procedure to print a number stored in AX
print_numar proc
    xor cx, cx    ; Clear CX 
    mov bx, 10    ; Set BX to 10 

convert:
    ; Convert the number in AX to individual digits
    xor dx, dx    ; Clear DX 
    div bx        ; Divide AX by 10, result in AX, remainder in DX
    push dx       ; Push the remainder onto the stack
    inc cx        ; Increment CX 
    test ax, ax   ; Check if AX is zero
    jnz convert   ; If AX is not zero, repeat the process

print_cifra:
    ; Pop each digit from the stack and print it
    pop dx        ; Pop a digit from the stack into DX
    add dl, '0'   ; Convert the digit to its ASCII representation
    mov ah, 02h   ; Set AH to 02h
    int 21h       ; Call interrupt 21h to print the character in DL
    loop print_cifra  ; Repeat for all digits in CX

    ret           ; Return from the procedure
print_numar endp

text_ ENDS

END start
