package merlen.antoine.cms

import java.sql.*
import java.util.concurrent.*

class ConnectionPool(val url: String, val user: String, val password: String) {

    private val queue = ConcurrentLinkedQueue<Connection>()


    fun getConnection(): Connection {
        val connection = queue.poll()

        if (connection == null) {
            return DriverManager.getConnection(url, user, password)
        } else {
            return connection
        }
    }

    fun releaseConnection(c: Connection) {
        queue.add(c)
        // or
        // list.add(c)
    }

    inline fun useConnection(f: (Connection) -> Unit) {
        val connection = getConnection()
        try {
            f(connection)
        } finally {
            releaseConnection(connection)
        }
    }
}