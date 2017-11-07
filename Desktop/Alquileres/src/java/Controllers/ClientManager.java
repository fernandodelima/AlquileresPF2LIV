/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Client;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import Controllers.DataAccess;
import Model.VMSpDatosCliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientManager {
        private Connection conn; 
/*
    String Nombre_Cliente;
    String Apellido_Cliente;
    String Mail_Cliente;
    String Telefono;
    int Documento;
    int Tipo_Docuento;
*/

    public ClientManager() {
        DataAccess da = new DataAccess();
        try {
            conn = DriverManager.getConnection(da.getCONN_STR(), da.getUSER(), da.getPASS());
        } catch (SQLException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public boolean AddNewClient(Client client){
        boolean isSuccessful = true;
        try {
            PreparedStatement stmt = conn.prepareStatement("EXEC sp_insert_cliente ?, ?, ?, ?, ?, ?");
            stmt.setString(1, client.getClientName());
            stmt.setString(2, client.getClientSurename());
            stmt.setString(3, client.getClientMail());
            stmt.setString(4, client.getPhone());
            stmt.setInt(5, client.getDocument());
            stmt.setInt(6,client.getDocumentType());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
            isSuccessful = false;
        }
        return isSuccessful;
    }
    
    public ArrayList<Client> GetClients(){
        ArrayList<Client> clients = new ArrayList<Client>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet query = stmt.executeQuery("SELECT c.id_cliente id, c.nombre_cliente nombre, c.apellido_cliente apellido, c.mail_cliente mail, c.telefono telefono, c.documento documento, c.tipo_documento tipo FROM Clientes c");
            while (query.next()) {
                Client c = new Client();
                c.setClientSurename(query.getString("apellido"));
                c.setClientName(query.getString("nombre"));
                c.setDocument(query.getInt("documento"));
                c.setClientMail(query.getString("mail"));
                c.setPhone(query.getString("telefono"));
                c.setIdClient(query.getInt("id"));
                c.setDocumentType(query.getInt("tipo"));
                clients.add(c);
            }
            query.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clients;
    }
    
    //
    public VMSpDatosCliente GetClientData(int idClient){
        VMSpDatosCliente clientData = new VMSpDatosCliente();
        try {
            Statement stmt = conn.createStatement();
            ResultSet query = stmt.executeQuery("EXEC sp_mostrar_datos_cliente_por_cliente " + idClient);
            if (query.next()) {
                clientData.setApellido(query.getString("Apellido"));
                clientData.setNombre(query.getString("Nombre"));
                clientData.setMail(query.getString("Mail"));
                clientData.setTelefono(query.getString("Telefono"));
                clientData.setTipo_documento(query.getString("Tipo documento"));
                clientData.setDocumento(query.getInt("Documento"));
                clientData.setImporte_mensual(query.getDouble("Importe Mensual"));
            }
            query.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientData;
    }
}
