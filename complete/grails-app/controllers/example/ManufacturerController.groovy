// tag::packageDeclaration[]
package example
// end::packageDeclaration[]

// tag::importStatements[]
import grails.gorm.transactions.ReadOnly
import grails.compiler.GrailsCompileStatic
import org.grails.datastore.mapping.multitenancy.web.SessionTenantResolver
// end::importStatements[]

// tag::classDeclaration[]
@GrailsCompileStatic
class ManufacturerController {
// end::classDeclaration[]

    // tag::index[]
    @ReadOnly
    def index() {
        render view: '/index', model: [manufacturers: Manufacturer.list()]
    }
    // end::index[]

    // tag::select[]
    @ReadOnly
    def select(String id) {
        Manufacturer m = Manufacturer.where {
            name == id
        }.first() // <1>
        if ( m ) {
            session.setAttribute(SessionTenantResolver.ATTRIBUTE, m.name.toLowerCase()) // <2>
            redirect controller: 'vehicle' // <3>
        }
        else {
            render status: 404
        }
    }
    // end::select[]

// tag::closeBracket[]
}
// end::closeBracket[]