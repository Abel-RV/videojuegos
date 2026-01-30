package psp.ut03.threads;

import psp.ut03.ModeloVideojuegos;
import psp.ut03.Videojuego;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HiloCliente extends Thread {
    private Socket socket;
    private ModeloVideojuegos modeloVideojuegos;

    public HiloCliente(Socket socket, ModeloVideojuegos modeloVideojuegos) {
        this.socket = socket;
        this.modeloVideojuegos = modeloVideojuegos;
    }

    @Override
    public void run() {
        try(
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        ){
            String comando;
            while(true){
                comando = dis.readUTF();
                if(comando.equalsIgnoreCase("END")){
                    break;
                }

                switch (comando) {
                    case "GET-GEN"->{
                        List<String> generos = modeloVideojuegos.getGeneros();
                        dos.writeInt(generos.size());
                        for(String genero : generos){
                            dos.writeUTF(genero);
                        }
                    }
                    case "GET-GENS"->{
                        List<String> desarrolladoras = modeloVideojuegos.getDesarrolladora();
                        dos.writeInt(desarrolladoras.size());
                        for(String desarrolladora : desarrolladoras){
                            dos.writeUTF(desarrolladora);
                        }
                    }
                    case "FILTER"->{
                        String titulo = dis.readUTF();
                        String genero = dis.readUTF();
                        String desarrolladora = dis.readUTF();

                        if(titulo.equals("NULL") || titulo.isEmpty())
                        {
                            titulo= null;
                        }
                        if(genero.equals("NULL") || genero.isEmpty())
                        {
                            genero= null;
                        }
                        if(desarrolladora.equals("NULL")||desarrolladora.equals("---Seleccione una opción---")){
                            desarrolladora= null;
                        }

                        List<Videojuego> lista = modeloVideojuegos.filterAll(titulo,genero,desarrolladora);
                        dos.writeInt(lista.size());
                        for(Videojuego videojuego : lista){
                            dos.writeInt(videojuego.getId());
                            dos.writeUTF(videojuego.getTitulo());
                            dos.writeUTF(videojuego.getGenero());
                            dos.writeUTF(videojuego.getDesarrolladora());
                            dos.writeInt(videojuego.getEdadLimite());
                        }
                    }
                    case "PORTADA"->{
                        int id = dis.readInt();
                        Videojuego v = modeloVideojuegos.getById(id);
                        boolean encontrada = false;

                        if(v!=null){
                            File archivo = new File(v.getRutaPortada());
                            if(archivo.exists()){
                                encontrada = true;
                                dos.writeBoolean(true);
                                dos.writeLong(archivo.length());

                                try(FileInputStream fis = new FileInputStream(archivo)){
                                    byte[] buffer = new byte[4096];
                                    int leidos;
                                    while((leidos = fis.read(buffer)) != -1){
                                        dos.write(buffer,0,leidos);
                                    }
                                }

                            }
                        }
                        if(!encontrada){
                            dos.writeBoolean(false);
                        }
                    }
                    default -> dos.writeUTF("Comando no valido");
                }
                dos.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally{
            try{
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
