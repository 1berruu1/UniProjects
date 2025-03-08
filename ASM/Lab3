; Lab3
; This program constructs a new array D from array S such that:
; - The odd-indexed elements of S come first in D.
; - The even-indexed elements of S come next in D.
; Example:
;   S = 1, 2, 3, 4, 5, 6, 7, 8
;   D = 1, 3, 5, 7, 2, 4, 6, 8

data_ SEGMENT
    S db 1, 2, 3, 4, 5, 6, 7, 8  ; Define the source array S with values 1 to 8
    len equ $-S                   ; Calculate the length of S 
    D db len dup(?)               ; Define the destination array D with the same length as S
data_ ENDS
text_ SEGMENT
start:
    mov ax, data_  ; Move the address of the data segment into AX
    mov ds, ax     ; Set DS

  ; First loop: Copy odd-indexed elements from S to D
    mov si, 0      ; Initialize SI to 0 
    mov di, 0      ; Initialize DI to 0 
    mov cx, len    ; Set CX to the length of S 
repeta:
    test cx, 1     ; Test if CX is odd 
    jnz skip_par   ; If CX is odd, jump to skip_par 
    mov al, S[si]  ; Move the byte at S[SI] into AL 
    mov D[di], al  ; Move the byte in AL into D[DI] 
    add di, 1      ; Increment DI by 1
skip_par:
    add si, 1      ; Increment SI by 1 
    loop repeta    ; Decrement CX and loop back to repeta if CX is not zero

    ; Second loop: Copy even-indexed elements from S to D
    mov si, 0      ; Reset SI 
    mov cx, len    ; Reset CX
repeta2:
    test cx, 1     ; Test if CX is even
    jz skip_impar  ; If CX is even, jump to skip_impar 
    mov al, S[si]  ; Move the byte at S[SI] into AL 
    mov D[di], al  ; Move the byte in AL into D[DI] 
    add di, 1      ; Increment DI by 1 
skip_impar:
    add si, 1      ; Increment SI by 1 to move to the next possition in S
    loop repeta2   ; Reapeating the loop until CX gets to 0

    ; Program termination
    mov ah, 4Ch    
    int 21h        

text_ ENDS

END start
