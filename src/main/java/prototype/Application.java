package prototype;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.SimpleSelector;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.rdf.model.impl.StatementImpl;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

public class Application {

  public static void main(final String[] args) {

    final Model model = ModelFactory.createDefaultModel();
    model.setNsPrefix("foaf", FOAF.NS);

    final Resource jeffreyschultz = model.createResource("urn:prototype:person:jeffreyschultz", FOAF.Person)
            .addProperty(FOAF.name, "Jeffrey Schultz");
//            .addProperty(RDF.type, FOAF.Person);

    final Resource jennaschultz = model.createResource("urn:prototype:person:jennaschultz", FOAF.Person)
            .addProperty(FOAF.name, "Jenna Schultz");

//    jennaschultz.addProperty(FOAF.knows, jeffreyschultz);
//    jeffreyschultz.addProperty(FOAF.knows, jennaschultz);

    model.add(new StatementImpl(jeffreyschultz, FOAF.knows, jennaschultz));
    model.add(new StatementImpl(jennaschultz, FOAF.knows, jeffreyschultz));

    Literal terroristLiteral = model.createLiteral("terrorist");
    model.add(new StatementImpl(jeffreyschultz, RDFS.label, terroristLiteral));

    StmtIterator terroristIterator = model.listStatements(new SimpleSelector(FOAF.Agent, RDFS.label, (RDFNode) null) {
      @Override
      public boolean selects(Statement s) {
        return super.selects(s);
      }
    });
    while (terroristIterator.hasNext()) {
      System.out.println(terroristIterator.nextStatement().toString());
      System.out.println("Hello");
    }

//    model.write(System.out);
  }
}
