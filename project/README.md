# Abfahrtszeiten

Mit der Abfahrtszeiten App kann die Pendelstrecke ausgewählt werden, um eine persönliche Abfahrtszeiten-Tafel direkt auf dem Gerät zu erhalten. Dadurch kann ohne das Öffnen der SBB-App jede Verbindung stressfrei und ohne Rennen glücken. Zeiten bis zur Abfahrt und Verspätungen werden nahtlos aktualisiert.

## Datenquellen

### DiDok API (data.sbb.ch/api)
> Endpoint: https://data.sbb.ch/api/explore/v2.1/catalog/datasets/dienststellen-gemass-opentransportdataswiss/records?select=geopos%2C%20designationofficial%2C%20number&refine=isocountrycode%3A%22CH%22&limit=10&where=%22

Liste der Haltestellen des Öffentlichen Verkehrs der Schweiz. Die Didok-Liste wird vom Bundesamt für Verkehr erstellt. Das Original kann unter https://opendata.swiss/de/dataset/haltestellen-des-offentlichen-verkehrs bezogen werden. Diese API wird gratis von der SBB zur Verfügung gestellt. 

Durch die Funktionen der API können die Haltestellen der Schweiz direkt gefiltert und die Ergebnisse so aufbereitet werden, dass nur die gewünschten Informationen angezeigt werden. Die Daten werden im JSON-Format zurückgegeben:

```json
{
  "geopos": [
    47.376887,
    8.541694
  ],
  "designationofficial": "Zürich HB",
  "number": "8503000"
}
```

Die Daten werden anschliessend vom JSON-Format zu einem DiDok Objekt transformiert.


### Transport API (transport.opendata.ch/v1)
> Endpoint: http://transport.opendata.ch/v1/connections

Eine von Opendata.ch veröffentlichte API, welche detaillierte Verbindungspläne der schweizerischen öffentlichen Verkehrsmittel zur Verfügung stellt. Mit dem Angeben des Starts und Zielortes werden für die aktuelle Zeit Verbindungen retourniert.
Das Resultat ist sehr Detailiert mit vielen Informationen.

```json
{
  "connections": [
    "from": {...},
    "to": {
      "station": {...}
    },
    "duration":"00d00:43:00",
    ...
    "sections": {...}
  ],
  "from": {...},
  "to": {...},
  "stations": {...}
}
```

## Datenverarbeitung

### Suchen & Filtern & Matchen:
- Die DiDok API stellt uns Parameter zur Verfügung, die wir folgendermassen verwenden:
- File: DiDokApi
- Methode: getStations

Suche:
- Mittels "where" paramter wird nach Haltestellen gesucht, die den Suchbegriff enthalten.

Filtern:
- Nur haltestellen in der Schweiz werden zurückgegeben.
- Die Anzahl der Resultate wird auf 10 beschränkt.

Ausgewählte Felder:
- GeoPos
- DesignationOfficial
- Number

Natürlich hätten wir dies auch manuell filtern können, jedoch ist es effizienter, dies direkt in der API zu machen.

### Transformieren:
- Nachdem die Haltestellen durch die API gefunden wurden, werden diese in ein DiDok Objekt transformiert:
- File: DiDok
- Methode: parseStations

### Sortieren:
- Die Haltestellen werden Alphabetisch sortiert:
- File: DataProcessor
- Methode: sortStations


## Datenspeicherung

Die Daten werden lokal auf dem Gerät gespeichert. Es geht dabei lediglich um Benutzerfreundlichkeit, sodass der User beim öffnen nicht immer wieder seine gewünschte Strecke eingeben muss. Wir haben uns dabei für ein Plain Text File entschieden, da diese einfach zu handhaben sind und keine komplexen Datenstrukturen benötigen. Zudem wird dieses Format von fast allen Betriebssystemen unterstützt. Unsere Daten werden folgendarmassen gespeichert:

```plaintext
DesignationOfficial:DiDokNumber#DesignationOfficial:DiDokNumber
```

Auf der linken Seite steht der Name der Haltestelle und auf der rechten Seite die DiDok Nummer. Der Start- und Endpunkt werden durch einen Schrägstrich getrennt.