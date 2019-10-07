import org.junit.jupiter.api.BeforeEach
import java.util.*
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class UserTest {
    var adminRoles  = EnumSet.noneOf(Access::class.java)
    var superUserRoles   = EnumSet.noneOf(Access::class.java)
    var basicUserRoles   = EnumSet.noneOf(Access::class.java)
    var visitorRoles     = EnumSet.noneOf(Access::class.java)

    @BeforeEach
    fun setup(){
        this.adminRoles     = EnumSet.allOf(Access::class.java)
        this.superUserRoles      = EnumSet.complementOf(EnumSet.of(Access.EDIT))
        this.basicUserRoles      = EnumSet.of(Access.VIEW)
        this.visitorRoles        = EnumSet.of(Access.NONE)
    }

    @org.junit.jupiter.api.Test
    fun getAccess() {
        val admin       = User(adminRoles)
        val visitor     = User(visitorRoles)
        val basicUser   = User(basicUserRoles)

        assertTrue(admin.access.containsAll(adminRoles))
        assertFalse(visitor.access.containsAll(adminRoles))
        assertFalse(basicUser.access.containsAll(adminRoles))
        assertTrue(basicUser.access.containsAll(basicUserRoles))
    }
}