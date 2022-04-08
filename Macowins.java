
//Requerimiento 1
// Necesito lograr polimorfismo entre las clases y por eso utilizo una Interfaz.

class Prenda {
    Estado estado;
    Double precioBase;
    precio(){return precioBase;}
}

public Interface Estado{
    Double precioFinal(Double precioBase);
}


class Nueva implements Estado{
    Double precioFinal(Double precioBase){return precioBase;}
}


class Promocion implements Estado{
    Double descuento;
    Double precioFinal(Double precioBase){ return precioBase - descuento;}
}


class Liquidacion implements Estado{
     Double precioFinal(Double precioBase){ return precioBase * 0.5;}
}

// Requerimiento 2 (tipo de Prenda), me pareció mas conveniente utilizar ENUM, que me permite definir los tipos a usar
// y no dejar en manos del usuario la declaración de estos tipos, por ejemplo como String ya que podría poner cualquier nombre.

class Prenda{
    TipoPrenda tipo;
}

new Prenda(TipoPrenda);

public enum TipoPrenda{
    SACO, PANTALON,CAMISA
}

// Requerimiento 3

class Item{
    Integer cantidad;
    Prenda prenda;

    Double importe(){
        return prenda.precioFinal * cantidad;
    }
}

class Venta {
    List<Item> items;
    Date fecha;
    abstract Double conRecargo(importeBase){

    }
    Double importeFinal(){
        return conRecargo( items.sum(item -> item.importe()) );
    }
}

class VentaEfectivo extends Venta{

    @override
    Double conRecargo(importeBase)
    {
        return importeBase;
    }
}


class VentaTarjeta extends Venta{
    Integer cantidadDeCuotas;
    Double coeficiente;

    @override
    Double conRecargo(importeBase)
    {
        Double recargo = (coeficiente * cantidadDeCuotas) + importeBase * 1.01;
        return importeBase + recargo;
    }
}

class Negocio{
    List <Venta> ventas;

    gananciasDe(unDia){
        ventas.filter(venta -> venta.fecha == unDia).sum(venta -> venta.importeFinal());
    }
}


