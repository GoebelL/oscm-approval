package org.oscm.app.approval.json;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.oscm.types.enumtypes.Salutation;
import org.oscm.vo.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({User.class})
public class UserTest {

    private User user;
    private VOUserDetails voUserDetails;

    @Test
    public void testConstructor() {
        voUserDetails = new VOUserDetails();

        voUserDetails.setUserId("JacobSmith");
        voUserDetails.setOrganizationId("Smiths organization");
        voUserDetails.setKey(11000);
        voUserDetails.setAdditionalName("Jan");
        voUserDetails.setAddress("Australia\nSmall Village 10");
        voUserDetails.setEMail("jacob.smith@email.com");
        voUserDetails.setFirstName("Jacob");
        voUserDetails.setLastName("Smith");
        voUserDetails.setLocale("en");
        voUserDetails.setPhone("123456789");
        voUserDetails.setSalutation(Salutation.MR);
        voUserDetails.setRealmUserId("JacobS");

        user = new User(voUserDetails);

        assertEquals("Jacob", user.firstname);
    }
}
