--ej 6a
select codigoprograma, sum(monto) as MontoMesual from ciudaddelosninios.donacion where frecuencia = 'Mensual' group by codigoprograma;

--ej6b
select d.dni from ciudaddelosninios.Donacion d, ciudaddelosninios.Donacion d2 where d.dni=d2.dni and d.codigoprograma<>d2.codigoprograma;

--ej6c
 select dni, nombre as nombre_donante, apellido as apellido_donante,m.identificador as id_medio_de_pago,
 m.nombretitular as nombre_titular, m.apellidotitular as apellido_titular, t.*
 from (ciudaddelosninios.Donacion d natural join ciudaddelosninios.MedioDePago m 
 natural join ciudaddelosninios.Persona p) 
join ciudaddelosninios.TarjetaDeCredito t on (m.identificador=t.id)
 where frecuencia='Mensual';

 select dni, p.nombre as nombre_donante, p.apellido as apellido_donante,m.identificador as id_medio_de_pago,
 m.nombretitular as nombre_titular, m.apellidotitular as apellido_titular, t.*
 from (ciudaddelosninios.Donacion d natural join ciudaddelosninios.MedioDePago m 
 natural join ciudaddelosninios.Persona p) 
 join ciudaddelosninios.TarjetaDeDebitoOTransferencia t on (m.identificador=t.id)
 where frecuencia='Mensual';
