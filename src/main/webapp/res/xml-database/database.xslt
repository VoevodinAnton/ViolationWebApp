<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">

        <html>
            <head>
                <title>Импорт</title>
            </head>
            <body>
                <h2>База данных автомобилей</h2>
                <table>
                    <tr>
                        <th>id</th>
                        <th>Номер</th>
                        <th>Модель</th>
                        <th>Владелец</th>
                    </tr>
                    <xsl:for-each select="CARS/CAR">
                        <xsl:sort/>
                            <tr>
                                <td><xsl:value-of select="ID"/></td>
                                <td><xsl:value-of select="NUMBER"/></td>
                                <td><xsl:value-of select="MODEL"/></td>
                                <td><xsl:value-of select="OWNER"/></td>
                            </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>