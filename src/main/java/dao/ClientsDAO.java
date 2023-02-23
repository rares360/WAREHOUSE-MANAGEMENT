package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Clients;

public class ClientsDAO extends AbstractDAO<Clients> {
    public ClientsDAO() {
        super();
    }
}
