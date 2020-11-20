/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: 17.11.2020
 *
 * <p>*****************************************************************************
 */
package org.oscm.app.approval.util;

import java.math.BigDecimal;

import org.oscm.app.approval.i18n.Messages;
import org.oscm.app.approval.json.PriceModel;
import org.oscm.internal.types.enumtypes.PriceModelType;
/** @author goebel */
public class PriceText {
  static final String LABEL_PRICE_MODEL_FREE = "priceModel.text.free";
  static final String LABEL_PRICE_MODEL_PRICE_AND_UNIT = "priceModel.text.combinePriceAndUnit";
  static final String LABEL_PRICE_MODEL_PRICE = "priceModel.text.price";
  static final String LABEL_PRICE_MODEL_PER_SUB = "priceModel.text.perSubscription";
  static final String LABEL_PRICE_MODEL_PER_USER = "priceModel.text.perUser";
  static final String LABEL_PRICE_MODEL_SEE_DETAILS = "priceModel.text.seeDetails";

  public static String from(PriceModel priceModel) {
    if (PriceModelType.FREE_OF_CHARGE.name().equalsIgnoreCase(priceModel.type)) {
      return getText(LABEL_PRICE_MODEL_FREE, null);
    }
    String[] result = new String[] {"", "", ""};
    if (isSet(priceModel.pricePerPeriod)) {
      result[0] =
          getText(
              LABEL_PRICE_MODEL_PRICE,
              new Object[] {priceModel.currency, priceModel.pricePerPeriod});
      result[1] =
          getText(LABEL_PRICE_MODEL_PER_SUB, new Object[] {getPeriodText(priceModel.period)});

    } else if (isSet(priceModel.pricePerUser)) {
      result[0] =
          getText(
              LABEL_PRICE_MODEL_PRICE, new Object[] {priceModel.currency, priceModel.pricePerUser});
      result[1] =
          getText(LABEL_PRICE_MODEL_PER_USER, new Object[] {getPeriodText(priceModel.period)});
    } else if (isSet(priceModel.oneTimeFee)) {
      return getText(
          LABEL_PRICE_MODEL_PRICE, new Object[] {priceModel.currency, priceModel.oneTimeFee});
    } else {
      return getText(LABEL_PRICE_MODEL_SEE_DETAILS, new Object[0]);
    }
    return getText(LABEL_PRICE_MODEL_PRICE_AND_UNIT, new Object[] {result[0], result[1]});
  }

  static final String getPeriodText(String period) {
    return getText("PricingPeriod." + period, new Object[0]);
  }

  static boolean isSet(String val) {
    if (val == null || val.trim().length() == 0) {
      return false;
    }
    return 1 == new BigDecimal(val).compareTo(BigDecimal.ZERO);
  }

  private static String getText(String key, Object... objects) {
    return Messages.get(key, objects);
  }
}
