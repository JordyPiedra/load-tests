# Práctica 1 - Solución


● Investigar y definir dónde está el error

Hay un problema de recurrencia, se solicitan peticiones simultaneas y al obtener el ultimo estado
Del evento , éste no está del todo actualizado.

● Proponer una solución al problema  

Agregar locks al momento de ingresar a una determinada función

Otra alternativa es enviarlos a una cola y que se procesen ordenadamente.

Solución:

Se agregó la siguiente annotation 
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Event bookTicket(Long eventId) 

