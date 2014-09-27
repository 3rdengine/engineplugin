<?php
/**
 *
 *
 * @copyright 2014 Third Engine Software / Latent Codex
 */

namespace [[namespace]]\[[bundle]]\Controller;

use SOA\SOABundle\Controller\ModelBasedServiceController;
use SOA\SOABundle\Model\SymfonyClassInfo;
use SOA\SOABundle\Interfaces\Collectionable;
use SOA\SOABundle\Http\PropelSOASuccessResponse;
use SOA\SOABundle\Http\PropelSOAErrorResponse;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;


/**
 * @route [[fullRoute]]
 */
class [[entity]]Controller extends ModelBasedServiceController implements Collectionable
{
  /**
   * This method points our controller to the proper table.
   */
  public function setupClassInfo()
  {
    $this->classInfo = new SymfonyClassInfo();

    $this->classInfo->namespace = '[[namespace]]';
    $this->classInfo->bundle    = '[[shortBundle]]';
    $this->classInfo->entity    = '[[entity]]';
  }

  /**
   * @Route("[[shortRoute]]")
   * @Method({"GET"})
   *
   * @param Request $request
   */
  public function getAction(Request $request)
  {
    return parent::getAction($request);
  }
}
