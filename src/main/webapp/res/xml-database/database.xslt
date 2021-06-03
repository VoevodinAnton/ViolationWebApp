<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="database">
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
                    <xsl:for-each select="cars/car">
                        <xsl:sort/>
                            <tr>
                                <td><xsl:value-of select="idc"/></td>
                                <td><xsl:value-of select="number"/></td>
                                <td><xsl:value-of select="model"/></td>
                                <td><xsl:value-of select="owner"/></td>
                            </tr>
                    </xsl:for-each>
                </table>
                <h2>База данных штрафов</h2>
                <table>
                    <tr>
                        <th>id</th>
                        <th>Тип</th>
                        <th>Размер</th>
                    </tr>
                    <xsl:for-each select="fines/fine">
                        <xsl:sort/>
                        <tr>
                            <td><xsl:value-of select="idf"/></td>
                            <td><xsl:value-of select="type"/></td>
                            <td><xsl:value-of select="amount"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
                <h2>База данных правонарушений</h2>
                <table>
                    <tr>
                        <th>id</th>
                        <th>id автомобиля</th>
                        <th>id штрафа</th>
                        <th>Дата</th>
                        <th>Место происшествия</th>
                        <th>Статус</th>
                    </tr>
                    <xsl:for-each select="violations/violation">
                        <xsl:sort/>
                        <tr>
                            <td><xsl:value-of select="idv"/></td>
                            <td><xsl:value-of select="id_car"/></td>
                            <td><xsl:value-of select="id_fine"/></td>
                            <td><xsl:value-of select="date"/></td>
                            <td><xsl:value-of select="address"/></td>
                            <td><xsl:value-of select="status"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>